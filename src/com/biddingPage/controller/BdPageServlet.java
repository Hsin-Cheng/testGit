package com.biddingPage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.bidding.model.BdRedis;
import com.bidding.model.BiddingService;
import com.bidding.model.BiddingVO;

@WebServlet("/biddingPage/BdPageServlet")
public class BdPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();

		// =====================front page action ===================
		
		
		//=======================================================
		if("setBidInfo".equals(action)) {
			try {
			String memId=req.getParameter("memId");
			String bdNo=req.getParameter("bdNo");
			
			req.setAttribute("memId",memId);
			req.setAttribute("bdNo",bdNo);
			BdRedis bdr= new BdRedis();	
			bdr.registerBdNo(bdNo);
			
			RequestDispatcher view=req.getRequestDispatcher("/frontend/biddingFront/biddingPage.jsp");
			view.forward(req, res);
			}catch(Exception e){
				e.printStackTrace();
//				System.out.println("(BdPageServlet) getBidInfo error:"+ e.getMessage());
//				throw new RuntimeException("(BdPageServlet) getBidInfo error:"+e.getMessage());
			}
		}
		
		
		// ======================================
		if ("newBid".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
			
				try {
					Integer bid = new Integer(req.getParameter("bid").trim());
					String memId= req.getParameter("memId");
					String bdNo = req.getParameter("bdNo");
				
				
					BdRedis bdr = new BdRedis();

					Integer currentBid = bdr.getTopBid(bdNo);

					if (currentBid < bid) {

						bdr.newBid(bdNo, memId , bid);

						System.out.println("(BdPageServlet)bid:" + bid);
						out.print(bid);
						
					} else {
						return;
					}
				} catch (NumberFormatException e) {
					System.out.println("(BdPageServlet) newBid error:"+ e.getMessage());
					errorMsgs.add("please enter the correct bid!:" + e.getMessage());
				}

			} catch (Exception e) {
				System.out.println("(BdPageServlet) newBid error:"+ e.getMessage());
				errorMsgs.add("(BdPageServlet)error!:" + e.getMessage());
			}

		}
		// ======================================
		if ("topBidders".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			
			try {
				
				String bdNo=null;
				BdRedis bdr = new BdRedis();
				JSONObject jsonObj = new JSONObject();
				
				try {
					
					bdNo = req.getParameter("bdNo");
//					System.out.println("bdNo:"+bdNo);
					// ================getting Top bidders============
					if (bdr.getBidsCount(bdNo) > 0) {
						
						String top1 = bdr.getTopBidders(bdNo)[0];
						Integer price1 = bdr.getBid(bdNo, top1);
					
						jsonObj.put("top1", top1);
						jsonObj.put("price1", price1);
					}
					if (bdr.getBidsCount(bdNo) > 1) {
						
						String top2 = bdr.getTopBidders(bdNo)[1];
						Integer price2 = bdr.getBid(bdNo, top2);
						
						jsonObj.put("top2", top2);
						jsonObj.put("price2", price2);
					}
					if (bdr.getBidsCount(bdNo) > 2) {
						
						String top3 = bdr.getTopBidders(bdNo)[2];
						Integer price3 = bdr.getBid(bdNo, top3);
					
						jsonObj.put("top3", top3);
						jsonObj.put("price3", price3);
					}
					Integer numberOfBids=bdr.getBidsCount(bdNo);
					jsonObj.put("numberOfBids", numberOfBids);
					
					
					out.print(jsonObj);

				} catch (NumberFormatException e) {
					System.out.println("(BdPageServlet)topBidders error1:" + e.getMessage());
					errorMsgs.add("please enter the correct bid!:" + e.getMessage());
				}

			} catch (Exception e) {
				System.out.println("(BdPageServlet)topBidders error2:" + e.getMessage());
				errorMsgs.add("(BdPageServlet)error!:" + e.getMessage());
			}
		}
	//=============bidOver======================
		if("StoreEndResult".equals(action)) {
			String bdNo=null;
			BdRedis bdr = new BdRedis();
			BiddingVO bVO=new BiddingVO();
			BiddingService bdSvc= new BiddingService();
			try {
				bdNo=req.getParameter("bdNo");
				bdr.storeDB(bdNo);
				
				
			}catch(Exception e) {
				throw new RuntimeException("(BdPageServlet) bidOver error:"+e.getMessage());
			}
		}

	}
}
