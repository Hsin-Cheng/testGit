package com.painter.model;

import java.sql.Timestamp;
import java.util.List;

public class PainterService {

	private PtRedis ptr;
	private PainterDAO_interface dao;

	public PainterService() {
		dao = new PainterDAO();
	}

	public PainterVO insert(String mem_id, String ptr_nm, String intro, byte[] pic, Integer priv_stat, Integer ptr_stat,
			Integer like_cnt, Integer col_cnt, Timestamp create_dt) {

		PainterVO pVO = new PainterVO();

		pVO.setMem_id(mem_id);
		pVO.setPtr_nm(ptr_nm);
		pVO.setIntro(intro);
		pVO.setPic(pic);
		pVO.setPriv_stat(priv_stat);
		pVO.setPtr_stat(ptr_stat);
		pVO.setLike_cnt(like_cnt);
		pVO.setCol_cnt(col_cnt);
		pVO.setCreate_dt(create_dt);

		dao.insert(pVO);
		
		return pVO;

	}

	public PainterVO update(Integer ptr_no, String mem_id, String ptr_nm, String intro, byte[] pic, Integer priv_stat,
			int ptr_stat, Integer like_cnt, Integer col_cnt, Timestamp create_dt) {

		PainterVO pVO = new PainterVO();

		pVO.setPtr_no(ptr_no);
		pVO.setMem_id(mem_id);
		pVO.setPtr_nm(ptr_nm);
		pVO.setIntro(intro);
		pVO.setPic(pic);
		pVO.setPriv_stat(priv_stat);
		pVO.setPtr_stat(ptr_stat);
		pVO.setLike_cnt(like_cnt);
		pVO.setCol_cnt(col_cnt);
		pVO.setCreate_dt(create_dt);

		dao.update(pVO);
		return pVO;
	}
	public PainterVO getPainterVO(Integer ptr_no) {
		return dao.findByPrimaryKey(ptr_no);
	}

	public void delete(Integer ptr_no) {
		dao.delete(ptr_no);
	}

	public List<PainterVO> getLatestPics() {
		return dao.latestPics();
	}
	
	public List<PainterVO> getMostLiked(Integer rankBegin,Integer rankEnd){
		return dao.getMostLiked(rankBegin, rankEnd);
	}

	public List<PainterVO> getAll() {
		return dao.getAll();
	}
	public Integer getPicCount() {
		return dao.getPicCount();
	}
	
}