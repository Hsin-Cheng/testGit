package com.painterTag.model;

import java.util.List;

public class PainterTagService {
	 
	private PainterTagDAO_interface dao;
	
	public PainterTagService(){
		dao = new PainterTagDAO();
	}
	
	
	public void addTag (String tag) {
	  PainterTagVO painterTagVO =new PainterTagVO();
	  painterTagVO.setTag_desc(tag);
		dao.insert(tag);

	}
	public List<Integer> getPicbyTag(String tag_desc){
		
		return dao.getPicbyTag(tag_desc);
	}
	
	public List<PainterTagVO> fuzzySearch(String tag_desc){
		
		return dao.fuzzySearch(tag_desc);
	}
	
	public Integer getPainterTagNo(String tag_desc) {
		return dao.getTagNo(tag_desc);
	}
	public String getPainterTagDesc(Integer tag_no) {
		return dao.getTagDesc(tag_no);
	}
	
} 
