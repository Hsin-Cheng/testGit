package com.painter.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.eclipse.jdt.internal.compiler.batch.Main;

import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPTZ;

public class PainterDAO implements PainterDAO_interface {

	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA103G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO painter( ptr_no, mem_id, ptr_nm, intro, pic, priv_stat, ptr_stat, like_cnt, col_cnt, create_dt) VALUES( painter_seq.nextval,?,?,?,?,?,?,?,?,?) ";
	private static final String UPDATE_STMT = "UPDATE painter SET  mem_id=?, ptr_nm=?, intro=?, pic=?, priv_stat=?, ptr_stat=?,like_cnt=?,col_cnt=?, create_dt=? WHERE ptr_no=? ";
	private static final String DELETE_STMT = "DELETE FROM painter WHERE ptr_no =?";
	private static final String FIND_BY_PRIMARY_KEY_STMT = "SELECT * FROM painter WHERE ptr_no=?";
	private static final String SEARCH_ALL = "SELECT * FROM painter";
	private static final String SEARCH_NEW_STMT = "SELECT * FROM painter ORDER BY create_dt ";
	
	private static final String SEARCH_MOST_LIKED_STMT="SELECT * FROM"
			+ "(SELECT ROW_NUMBER() OVER (ORDER BY LIKE_CNT desc)"
			+ "AS RANK,PAINTER.* FROM PAINTER WHERE CREATE_DT BETWEEN ? AND ?)"
			+ "WHERE RANK BETWEEN ? AND ?";

	private static final String GET_PIC_COUNT="SELECT COUNT(*) FROM PAINTER";

	
	public void insert(PainterVO painterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, painterVO.getMem_id());
			pstmt.setString(2, painterVO.getPtr_nm());
			pstmt.setString(3, painterVO.getIntro());
			pstmt.setBytes(4, painterVO.getPic());
			pstmt.setInt(5, painterVO.getPriv_stat());
			pstmt.setInt(6, painterVO.getPtr_stat());
			pstmt.setInt(7, painterVO.getLike_cnt());
			pstmt.setInt(8, painterVO.getCol_cnt());
			pstmt.setTimestamp(9, painterVO.getCreate_dt());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("database error" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("insert successfully");
	}

	public void update(PainterVO painterVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, painterVO.getMem_id());
			pstmt.setString(2, painterVO.getPtr_nm());
			pstmt.setString(3, painterVO.getIntro());
			pstmt.setBytes(4, painterVO.getPic());
			pstmt.setInt(5, painterVO.getPriv_stat());
			pstmt.setInt(6, painterVO.getPtr_stat());
			pstmt.setInt(7, painterVO.getLike_cnt());
			pstmt.setInt(8, painterVO.getCol_cnt());
			pstmt.setTimestamp(9, painterVO.getCreate_dt());

			pstmt.setInt(10, painterVO.getPtr_no());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("database error" + e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("update successfully");
	}

	public void delete(Integer ptr_no) { //not using, just preparing
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, ptr_no);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("database error" + e.getMessage());
		}
		System.out.println("delete successfully");
	}
	
	public PainterVO findByPrimaryKey(Integer ptr_no) {
		
		PainterVO pVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PRIMARY_KEY_STMT);

			pstmt.setInt(1, ptr_no);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				pVO = new PainterVO();
				
				pVO.setPtr_no(rs.getInt("ptr_no"));
				pVO.setMem_id(rs.getString("mem_id"));
				pVO.setPtr_nm(rs.getString("ptr_nm"));
				pVO.setIntro(rs.getString("intro"));
				pVO.setPic(rs.getBytes("pic"));
				pVO.setPriv_stat(rs.getInt("priv_stat"));
				pVO.setPtr_stat(rs.getInt("ptr_stat"));
				pVO.setLike_cnt(rs.getInt("like_cnt"));
				pVO.setCol_cnt(rs.getInt("col_cnt"));
				pVO.setCreate_dt(rs.getTimestamp("create_dt"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("database error" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return pVO;
	}

	public List<PainterVO> latestPics() {

		PainterVO painterVO = null;
		List<PainterVO> list = new ArrayList<PainterVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_NEW_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				painterVO = new PainterVO();
				painterVO.setPtr_no(rs.getInt("ptr_no"));
				painterVO.setMem_id(rs.getString("mem_id"));
				painterVO.setPtr_nm(rs.getString("ptr_nm"));
				painterVO.setIntro(rs.getString("intro"));
				painterVO.setPic(rs.getBytes("pic"));
				painterVO.setPriv_stat(rs.getInt("priv_stat"));
				painterVO.setPtr_stat(rs.getInt("ptr_stat"));
				painterVO.setLike_cnt(rs.getInt("like_cnt"));
				painterVO.setCol_cnt(rs.getInt("col_cnt"));
				painterVO.setCreate_dt(rs.getTimestamp("create_dt"));
				list.add(painterVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("dataBase error" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
		return list;
	}
	public List<PainterVO>getMostLiked(Integer rankBegin, Integer rankEnd){
		
		List<PainterVO> list =new ArrayList<PainterVO>();
		PainterVO painterVO=null;
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;
		
		try {
			con = ds.getConnection();
			pstmt=con.prepareStatement(SEARCH_MOST_LIKED_STMT);

			Date d = new Date();
			
			Timestamp past =new Timestamp(d.getTime()-(2*60*60*24*30*1000L));
			Timestamp now =new Timestamp(d.getTime());
			

			pstmt.setTimestamp(1,past);
			pstmt.setTimestamp(2,now);
			pstmt.setInt(3, rankBegin);
			pstmt.setInt(4, rankEnd);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				painterVO=new PainterVO();
				painterVO.setPtr_no(rs.getInt("ptr_no"));
				painterVO.setMem_id(rs.getString("mem_id"));
				painterVO.setPtr_nm(rs.getString("ptr_nm"));
				painterVO.setIntro(rs.getString("intro"));
				painterVO.setPic(rs.getBytes("pic"));
				painterVO.setPriv_stat(rs.getInt("priv_stat"));
				painterVO.setPtr_stat(rs.getInt("ptr_stat"));
				painterVO.setLike_cnt(rs.getInt("like_cnt"));
				painterVO.setCol_cnt(rs.getInt("col_cnt"));
				painterVO.setCreate_dt(rs.getTimestamp("create_dt"));
				list.add(painterVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error:"+e.getMessage());
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public List<PainterVO> getAll() {

		PainterVO painterVO = null;
		List<PainterVO> list = new ArrayList<PainterVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_ALL);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				painterVO = new PainterVO();
				painterVO.setPtr_no(rs.getInt("ptr_no"));
				painterVO.setMem_id(rs.getString("mem_id"));
				painterVO.setPtr_nm(rs.getString("ptr_nm"));
				painterVO.setIntro(rs.getString("intro"));
				painterVO.setPic(rs.getBytes("pic"));
				painterVO.setPriv_stat(rs.getInt("priv_stat"));
				painterVO.setPtr_stat(rs.getInt("ptr_stat"));
				painterVO.setLike_cnt(rs.getInt("like_cnt"));
				painterVO.setCol_cnt(rs.getInt("col_cnt"));
				painterVO.setCreate_dt(rs.getTimestamp("create_dt"));
				list.add(painterVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("dataBase error" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public Integer getPicCount() {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer count=0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PIC_COUNT);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				count=rs.getInt(1);
			}

		} catch (SQLException e) {
			throw new RuntimeException("dataBase error" + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}

			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
//		System.out.println(count);
		return count;
	}

	public static void main(String[] args) {
		PainterDAO dao = new PainterDAO();
		PainterVO pVO = new PainterVO();
		Timestamp t = new Timestamp(0);
		byte[] b = new byte[1024];

		pVO.setPtr_no(2);
		pVO.setMem_id("M000001");
		pVO.setPtr_nm("Name of Painting");
		pVO.setIntro("³o¸Ì¬OÂ²¤¶2");
		pVO.setPic(b);
		pVO.setPriv_stat(0);
		pVO.setPtr_stat(0);
		pVO.setLike_cnt(112);
		pVO.setCol_cnt(123);
		pVO.setCreate_dt(t);

		//=======================insert================
//		dao.insert(pVO);
		
		//======================update==================
//		dao.update(pVO);
		
		//=====================delete=====================
//		dao.delete(pVO.getPtr_no());
		
		//=====================FIND_BY_PRIMARY_KEY==============
//		dao.findByPrimaryKey(pVO.getPtr_no());
		
		//==================latestPics====================
//		dao.latestPics();
		
		//==================mostLikedPics==================
//		dao.getMostLiked(2,5);
		
		//===================getAll==========================
//		List<PainterVO> list=dao.getAll();
//		for(PainterVO p :list) {
//			System.out.println(p.getCreate_dt());
//		}
		
		//=================getPicCount=======================
//		dao.getPicCount();
		
		
	}
}
