package com.painter.controller;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.painter.model.PainterDAO_JDBC;
import com.painter.model.PainterService;
import com.painter.model.PainterVO;

@WebServlet("/painter/ShowImage")
@MultipartConfig
public class ShowImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			ServletOutputStream out=res.getOutputStream();
			
			if(!req.getParameter("ptr_no").isEmpty()) {
			String ptr_no=req.getParameter("ptr_no");//取得作品編號
			PainterService ptSvc =new PainterService();
			PainterVO pt =ptSvc.getPainterVO(Integer.valueOf(ptr_no));
					byte[] b=pt.getPic();
					out.write(b);
			out.close();
			}
	}
}
