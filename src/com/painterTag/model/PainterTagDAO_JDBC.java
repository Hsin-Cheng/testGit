package com.painterTag.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.painter.model.PainterVO;

public class PainterTagDAO_JDBC implements PainterTagDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "G1";
	String password = "123456";

	private static final String INSERT_STMT = "INSERT INTO painter_tag (tag_no,tag_desc) VALUES (PAINTER_TAG_SEQ.nextval,?)";
	private static final String SEARCHBYTAG_STMT = "SELECT tag_no,tag_desc FROM painter_tag WHERE lower(tag_desc) LIKE ?";
	private static final String GET_TAG_DESC_STMT = "SELECT tag_no,tag_desc FROM painter_tag WHERE tag_no = ?";
	private static final String GET_PIC_BY_TAG_DESC_STMT="SELECT PTR_NO FROM PAINTER_TAG_MAP WHERE TAG_NO=(SELECT tag_no FROM painter_tag WHERE lower(tag_desc) LIKE ?)";
	
	public List<Integer> getPicbyTag(String tag) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> list=null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_PIC_BY_TAG_DESC_STMT);
			pstmt.setString(1, tag );
			rs = pstmt.executeQuery();
			list=new ArrayList<Integer>();
			
			while (rs.next()) {
				list.add(rs.getInt("ptr_no"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load data base driver");
		} catch (SQLException e) {
			throw new RuntimeException("A database error occur");
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
//		System.out.println("(PainterTagDAO)"+list);
		return list;
	}
	
	@Override
	public void insert(String tag) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, tag);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load data base driver");
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

	@Override
	public List<PainterTagVO> fuzzySearch(String tag) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<PainterTagVO> list = new ArrayList<PainterTagVO>();
		PainterTagVO painterTagVO = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(SEARCHBYTAG_STMT);
			pstmt.setString(1, "%" + tag + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				painterTagVO = new PainterTagVO();

				painterTagVO.setTag_no(rs.getInt("tag_no"));
				painterTagVO.setTag_desc(rs.getString("tag_desc"));

				list.add(painterTagVO);

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load data base driver");
		} catch (SQLException e) {
			throw new RuntimeException("A database error occur");
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
//		System.out.println(list);
		return list;
	}

	@Override
	public Integer getTagNo(String tag_desc) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		PainterTagVO painterTagVO = null;
		Integer tag_no=null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(SEARCHBYTAG_STMT);
			pstmt.setString(1, tag_desc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				painterTagVO = new PainterTagVO();
				tag_no=rs.getInt("tag_no");
				painterTagVO.setTag_no(rs.getInt("tag_no"));
				painterTagVO.setTag_desc(rs.getString("tag_desc"));

			

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load data base driver");
		} catch (SQLException e) {
			throw new RuntimeException("A database error occur");
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
//		System.out.println("ptVO > tag_no:"+tag_no);
		return tag_no;
	}

	@Override
	public String getTagDesc(Integer tag_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		PainterTagVO painterTagVO = null;
		String tag_desc=null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_TAG_DESC_STMT);
			pstmt.setInt(1, tag_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				painterTagVO = new PainterTagVO();
				tag_desc=rs.getString("tag_desc");
				painterTagVO.setTag_no(rs.getInt("tag_no"));
				painterTagVO.setTag_desc(rs.getString("tag_desc"));

			

			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load data base driver");
		} catch (SQLException e) {
			throw new RuntimeException("A database error occur");
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
//		System.out.println(tag_desc);
		return tag_desc;
	}

// ===============testing database===============
	public static void main(String[] args) {
		PainterTagDAO_JDBC dao = new PainterTagDAO_JDBC();
		
		// ======================insert====================
//		dao.insert("¤p¿ß¿ß");
		
		//=======================searchByTag======================
//		dao.searchByTag("¿ß");
		
		// =======================getTagNo===========================
//		dao.getTagNo("¿ß");
		//=======================getTagDesc=======================
//		dao.getTagDesc(1);
	}
}

