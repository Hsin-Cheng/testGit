package com.painter.model;

import java.util.List;

import com.painterTag.model.PainterTagVO;

public interface PainterDAO_interface {
	public void insert(PainterVO painterVO) ;
	public void update(PainterVO painterVO);
	public void delete(Integer ptr_no);
	public PainterVO findByPrimaryKey(Integer ptr_no);
	public List<PainterVO> latestPics();
	public List<PainterVO> getMostLiked(Integer rankBegin,Integer rankEnd);
	public List<PainterVO> getAll();
	public Integer getPicCount();
	
}
