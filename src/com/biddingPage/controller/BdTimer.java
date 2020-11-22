package com.biddingPage.controller;

import java.util.Timer;
import java.util.TimerTask;

import com.bidding.model.BdRedis;
import com.bidding.model.BiddingVO;

public class BdTimer {

	public void startTimer() {
		BdRedis bdr =new BdRedis();
		Timer timer =new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {	
				BiddingVO bVO=bdr.getRunningBd();
				bdr.storeDB(bVO.getBdNo());
				System.out.println("timer");
			}
		};
		timer.schedule(task, bdr.getBdEndTime());
	}
}
