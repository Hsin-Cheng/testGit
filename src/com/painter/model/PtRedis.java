package com.painter.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.painterTag.model.*;
import com.painterTagMap.model.PainterTagMapService;

import redis.clients.jedis.Jedis;

public class PtRedis {

	public PtRedis() {
		super();
	}
	public void insert(Integer ptr_no,PainterVO painterVO) {
//		Jedis jedis =new Jedis();
//		jedis.auth("123456");		
//		JSONObject json =new JSONObject();
//		
//		try {
//			
////			jedis.hset(ptr_no, field, value)
//	
////			pstmt.setString(1, painterVO.getMem_id()); // 會員編號
////			pstmt.setString(2, painterVO.getPtr_nm()); // 作品名稱
////			pstmt.setString(3, painterVO.getIntro()); // 作品介紹
////			pstmt.setBytes(4, painterVO.getPic()); // 作品圖片
////			pstmt.setInt(5, painterVO.getPriv_stat()); // 隱私權狀態
////			pstmt.setInt(6, painterVO.getPtr_stat()); // 作品狀態
////			pstmt.setInt(7, painterVO.getLike_cnt()); // 被喜歡次數
////			pstmt.setInt(8, painterVO.getCol_cnt()); // 被收藏次數
//
//			
//		catch(Exception e){
//			throw new RuntimeException("(PtRedis) setData error:")
//		}
	}
	


	public static void main(String[] args) {
		PtRedis bdr = new PtRedis();
//		bdr.newBid("B00001", "M0001", 150);
//		bdr.newBid("B00001", "M0002", 200);
//		bdr.getHighestBid("B00001");
//		System.out.println("(BdRedis) getTopBidders:"+bdr.getTopBidders("B00001")[0]);
//		System.out.println("(BdRedis)"+bdr.getBidsCount("B00001"));	
//		bdr.registerBdNo("B0001");
//		bdr.storeDB("B00003");
	}

}
