package com.painterTagMap.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.painterTag.model.PainterTagVO;

public class PainterTagMapDAO implements PainterTagMapDAO_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EA103G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO painter_tag_map (ptr_no,tag_no,tag_seq) VALUES (?,?,?)";
	private static final String DELETE = "DELETE FROM painter_tag_map WHERE ptr_no=?";
	private static final String UPDATE = "UPDATE painter_tag_map SET tag_no=?,tag_seq=? WHERE ptr_no=?";
	private static final String SEARCH_BY_PTR_NO = " SELECT * FROM painter_tag_map WHERE ptr_no=?";
	private static final String SEARCH_BY_TAG_NO = "SELECT * FROM painter_tag_map WHERE tag_no=?";
	private static final String SEARCH_SEQ = "SELECT tag_seq FROM painter_tag_map WHERE ptr_no=? AND tag_no=?";
	private static final String GET_ALL="SELECT * FROM painter_tag_map";
	
	
	
	public void insert(String tag) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tag);
			pstmt.executeUpdate();

	
		} catch (SQLException e) {
			throw new RuntimeException("A database error occur");
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
	}
	
	public void insert(PainterTagMapVO ptmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, ptmVO.getPtr_no());
			pstmt.setInt(2, ptmVO.getTag_no());
			pstmt.setInt(3, ptmVO.getTag_seq());

			pstmt.executeUpdate();


		} catch (SQLException e) {
			throw new RuntimeException("Database error occur:" + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("insert successfully");
	}

	public void delete(Integer ptr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ptr_no);

			pstmt.executeUpdate();

	
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured:" + e.getMessage());
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
		System.out.println("delete successfully");
	}
	
	public void update(PainterTagMapVO ptmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, ptmVO.getTag_no());
			pstmt.setInt(2, ptmVO.getTag_seq());
			pstmt.setInt(3, ptmVO.getPtr_no());

			pstmt.executeUpdate();

	
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured:" + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("update ptr_no:"+ptmVO.getPtr_no()+" successfully");
		System.out.println("tag_no="+ptmVO.getTag_no());
		System.out.println("tag_seq="+ptmVO.getTag_seq());
		
	}
	
	public List<PainterTagMapVO> searchByPtrNo(Integer ptr_no) {

		List<PainterTagMapVO> list = new ArrayList<PainterTagMapVO>();
		PainterTagMapVO ptVO=null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_BY_PTR_NO);

			pstmt.setInt(1, ptr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				ptVO= new PainterTagMapVO();
				ptVO.setPtr_no(rs.getInt("ptr_no"));
				ptVO.setTag_no(rs.getInt("tag_no"));
				ptVO.setTag_seq(rs.getInt("tag_seq"));
				
				list.add(ptVO);
			}

		
		} catch (SQLException e) {
			throw new RuntimeException("Database error occured:" + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("List<PainterTagMap>:"+list);
		return list;
		
	}
	
	public List<PainterTagMapVO> searchByTagNo(Integer tag_no) {
		
		List<PainterTagMapVO> list = new ArrayList<PainterTagMapVO>();
		PainterTagMapVO ptVO= new PainterTagMapVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_BY_TAG_NO);
			
			pstmt.setInt(1, tag_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ptVO= new PainterTagMapVO();
				ptVO.setPtr_no(rs.getInt("ptr_no"));
				ptVO.setTag_no(rs.getInt("tag_no"));
				ptVO.setTag_seq(rs.getInt("tag_seq"));
				
				list.add(ptVO);
			}
			

		} catch (SQLException e) {
			throw new RuntimeException("Database error occured:" + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("List<PainterTagMap>:"+list);
		return list;
	}

	public Integer searchSeq(Integer ptr_no, Integer tag_no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Integer seq = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(SEARCH_SEQ);

			pstmt.setInt(1, ptr_no);
			pstmt.setInt(2, tag_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				 seq =rs.getInt("tag_seq");
			}

		} catch (SQLException e) {
			throw new RuntimeException("Database error occured:" + e.getMessage());
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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
		System.out.println("tag_seq:"+seq);
		return seq;
	}
	
	public static void main(String[] args) {
		//==========================insert=============================
		PainterTagMapDAO dao=new PainterTagMapDAO();
		PainterTagMapVO ptmVO= new PainterTagMapVO();
		
		ptmVO.setPtr_no(1);
		ptmVO.setTag_no(6);
		ptmVO.setTag_seq(1);
		
		//=====================insert========================
//		dao.insert(ptmVO);
	
		//====================delete==============================
//		dao.delete(ptmVO.getPtr_no());
		
		//====================update==============================
//		dao.update(ptmVO);
		
		//====================searchByPtrNo==============================
//		dao.searchByPtrNo(ptmVO.getPtr_no());
		
		//================searchByTagNo(Integer)====================
		dao.searchByTagNo(ptmVO.getTag_no());
		
		//===============searchSeq===========================
//		dao.searchSeq(ptmVO.getPtr_no(), ptmVO.getTag_no());
		
	}
}
