package com.painter.model;

import java.io.*;
import java.util.*;

import javax.swing.filechooser.FileSystemView;

public class LoopForPic {
	public static void main(String[] args) {
		PainterDAO_JDBC dao = new PainterDAO_JDBC();
		PainterService pSvc = new PainterService();
		List<PainterVO> list = pSvc.getAll();
		byte[] pic = new byte[1024 * 15];
		
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File deskTop=fsv.getHomeDirectory();
		
	
		int i = 0;
		
		for (PainterVO p : list) {
			InputStream is =null;
			try {
				i++;
				is = new BufferedInputStream(new FileInputStream("C:/myWebApps/JavaEE_Hsin_workspace/G1/WebContent/imgData/img (" + i + ").jpg"));
				is.read(pic);
				pSvc.update(p.getPtr_no(),p.getMem_id(), p.getPtr_nm(), p.getIntro(), pic, p.getPriv_stat(), p.getPtr_stat(), p.getLike_cnt(), p.getCol_cnt(), p.getCreate_dt());
				System.out.println("更新完成");
			} catch (FileNotFoundException e) {
				System.out.println("更新失敗:");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("更新失敗:");
				e.printStackTrace();
			}finally {
				if(is!=null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
