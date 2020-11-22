package com.bidding.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.biddingPage.controller.BdTimer;

import redis.clients.jedis.Jedis;

public class BdRedis {

	public BdRedis() {
		super();
	}

//	public Long getCountDown() {
//		Jedis jedis =new Jedis("localhost",6379);
//		jedis.auth("123456");
//		
//		Long countDown=jedis.ttl("bdNo");
//		return countDown;
//	}
	public BiddingVO getRunningBd() {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		Timestamp bdDateStr = new Timestamp(Long.valueOf(jedis.get("startTime")));
		Timestamp bdDateEnd = new Timestamp(Long.valueOf(jedis.get("endTime")));

		BiddingVO bVO = new BiddingVO();

		bVO.setBdNo(jedis.get("bdNo"));
		bVO.setBdDateStr(bdDateStr);
		bVO.setBdDateEnd(bdDateEnd);

		jedis.close();
		return bVO;
	}

	public void registerBdNo(String bdNo) {
		if (isRegistered()) {
			return;
		}
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		if (jedis.exists(bdNo)) {
			jedis.close();
			return;
		}

		jedis.set("bdNo", bdNo);
//		jedis.expire("bdNo", (60 * 60 * 24 * 30));// sec

		Long startTime = System.currentTimeMillis();
		jedis.set("startTime", startTime.toString());

		Long endTime = startTime + (60 * 60 * 24 * 30 * 1000L);// millis
		jedis.set("endTime", endTime.toString());

		BiddingService bdSvc = new BiddingService();
		Timestamp startT = new Timestamp(Long.valueOf(startTime));
		Timestamp endT = new Timestamp(Long.valueOf(endTime));
		String sqlBdNo=bdSvc.insert("", 3001,0, startT, endT, 0, 0, 0, 0, "", "",
				"");
		//3001 bdProdNo
		
		// ======save another info to record========
		Map<String, String> map = new HashMap<String, String>();
		map.put("startTime", startTime.toString());
		map.put("endTime", endTime.toString());
		map.put("sqlBdNo",sqlBdNo);
		jedis.hmset("result:" + bdNo, map);

		
		// =======start Timer=============
		BdTimer timer= new BdTimer();
		timer.startTimer();
		// =======start Timer=============
		
		jedis.close();
	}

	public boolean isRegistered() {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		boolean b = jedis.exists("bdNo");

		jedis.close();
		return b;

	}

	public void newBid(String bdNo, String memId, Integer price) {
		if (isRegistered()) {
			Jedis jedis = new Jedis("localhost", 6379);
			jedis.auth("123456");

			jedis.zadd(bdNo, price, memId);
//		jedis.hmset(memId,"bdNo",bdNo,"bdProdNo",String.valueOf(bdProdNo),"bdPrice",String.valueOf(bdPrice),"bdDateStr",String.valueOf(bdDateStr),"bdDateEnd",String.valueOf(bdDateEnd),"bdStatus",String.valueOf(bdStatus),"bdOrdStatus",String.valueOf(bdOrdStatus),"pmtStatus",String.valueOf(pmtStatus),"bdZip",String.valueOf(bdZip),"bdName",bdName,"bdPhone",String.valueOf(bdPhone),"bdAddr",bdAddr);

			System.out.println("(BdRedis)new bid!");

			jedis.close();
		} else {
			System.out.println("(BdRedis) bdNo is not Registered");
			return;
		}
	}

	public Integer getTopBid(String bdNo) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		Set<String> set = jedis.zrevrange(bdNo, 0, 0);

		if (set.isEmpty()) {
			jedis.close();
			return 0;
		}
		Iterator<String> i = set.iterator();

		String highestBidder = i.next();
		Integer highestBid = jedis.zscore(bdNo, highestBidder).intValue();
//		System.out.println("(BdRedis)highestBidder:"+highestBidder);
//		System.out.println("(BdRedis)highestBid:"+highestBid);

		jedis.close();
		return highestBid;
	}

	public Integer getBid(String bdNo, String memId) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		Integer bid = jedis.zscore(bdNo, memId).intValue();

		jedis.close();
		return bid;
	}

	public String[] getTopBidders(String bdNo) {

		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		Set<String> set = jedis.zrevrange(bdNo, 0, 4);
//		System.out.println(set);
		Iterator<String> i = set.iterator();
		String[] topBidders = new String[set.size()];

		int n = 0;
		while (i.hasNext()) {
			topBidders[n] = i.next();
			n++;
		}

		jedis.close();
		return topBidders;
	}

	public Integer getBidsCount(String bdNo) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		Integer count = (jedis.zcard(bdNo)).intValue();

		jedis.close();
		return count;
	}

	public Timestamp getBdEndTime() {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");

		Timestamp bdDateEnd = new Timestamp(Long.valueOf(jedis.get("endTime")));

		jedis.close();
		return bdDateEnd;
	}

	public void storeDB(String bdNo) {
		BiddingService bdSvc = new BiddingService();
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.auth("123456");
		// =============================Redis��VO�e��Ʈw
		try {

			String startTime = jedis.hget("result:" + bdNo, "startTime");
			Timestamp startT = new Timestamp(Long.valueOf(startTime));
			String endTime = jedis.hget("result:" + bdNo, "endTime");
			Timestamp endT = new Timestamp(Long.valueOf(endTime));
			String sqlBdNo= jedis.hget("result"+bdNo, "sqlBdNo");

			if (bdSvc.getOne(sqlBdNo) != null) {
				bdSvc.update(sqlBdNo, getTopBidders(bdNo)[0], 3001, getTopBid(bdNo), startT, endT, 1, 0, 0, 0, null, null,
						null);
			} else {
				System.out.println("(BdRedis)storeDB error");
				return;
			}
			System.out.println("(BdRedis)insert from Redis to Oracle...");
			jedis.expire("bdNo", 0);
		} catch (Exception e) {
			throw new RuntimeException("(BdRedis) storeDB error:" + e.getMessage());
		} finally {
			jedis.close();
		}

	}

	public static void main(String[] args) {
//		BdRedis bdr = new BdRedis();
//		bdr.newBid("B00001", "M0001", 150);
//		bdr.newBid("B00001", "M0002", 200);
//		bdr.getHighestBid("B00001");
//		System.out.println("(BdRedis) getTopBidders:"+bdr.getTopBidders("B00001")[0]);
//		System.out.println("(BdRedis)"+bdr.getBidsCount("B00001"));	
//		bdr.registerBdNo("B0001");
//		bdr.storeDB("B00003");
	}

}
