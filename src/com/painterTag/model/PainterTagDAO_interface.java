package com.painterTag.model;

import java.util.List;

public interface PainterTagDAO_interface {
	public void insert(String tag) ;
	public List<Integer>getPicbyTag(String tag_desc);
	public Integer getTagNo(String tag_desc);
	public String getTagDesc(Integer tag_no);
	public List<PainterTagVO> fuzzySearch(String tag);

}
