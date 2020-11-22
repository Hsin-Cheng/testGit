package com.painterTagMap.model;

import java.util.ArrayList;
import java.util.List;

public class PainterTagMapService {
	
	private PainterTagMapDAO_interface dao;
	
	public PainterTagMapService(){
		dao= new PainterTagMapDAO();
	}
	
	public PainterTagMapVO addPainterTagMap(Integer ptr_no,Integer tag_no,Integer tag_seq) {
		PainterTagMapVO ptmVO= new PainterTagMapVO();
		ptmVO.setPtr_no(ptr_no);
		ptmVO.setTag_no(tag_no);
		ptmVO.setTag_seq(tag_seq);
		dao.insert(ptmVO);
		return ptmVO;
	}
	
	public void delete(Integer ptr_no) {
		dao.delete(ptr_no);
	}
	
	public PainterTagMapVO update(Integer ptr_no,Integer tag_no,Integer tag_seq) {
		PainterTagMapVO ptmVO= new PainterTagMapVO();
		ptmVO.setPtr_no(ptr_no);
		ptmVO.setTag_no(tag_no);
		ptmVO.setTag_seq(tag_seq);
		dao.update(ptmVO);
		return ptmVO;
	}
	
	public List<PainterTagMapVO> getPainterTagMapListByPtrno(Integer ptr_no) {
		
		return dao.searchByPtrNo(ptr_no);
		
	}
	
	public List<PainterTagMapVO> getListByTagno(Integer tag_no){
		return dao.searchByTagNo(tag_no);
	}
	
	public Integer getSeq(Integer ptr_no, Integer tag_no){
		return dao.searchSeq(ptr_no, tag_no);
	}
}

