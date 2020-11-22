package com.painterTagMap.model;

import java.util.List;

public interface PainterTagMapDAO_interface {
	public void insert(PainterTagMapVO ptmVO);
	public void delete(Integer ptr_no);
	public void update(PainterTagMapVO ptmVO);
	public List<PainterTagMapVO> searchByPtrNo(Integer ptr_no);
	public List<PainterTagMapVO> searchByTagNo(Integer tag_no);
	public Integer searchSeq(Integer ptr_no, Integer tag_no);
}
