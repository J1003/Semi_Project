package users;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.CommonJDBCUtil;



public class UsersDAO {

	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.18.6:1521:xe";
	private static final String USER = "project1";
	private static final String PASSWORD = "project1";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	
	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("[예외발생] JDBC 드라이버 로딩 실패!!!");
		}
	}
	//SELECT : 데이터 1개 조회(user_id)
	public UsersVO selectOne(String user_id) {
		UsersVO vo = null;
		
		try {
			conn =  CommonJDBCUtil.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT USER_ID, EMAIL, PASSWORD, PHONENUMBER, BIRTHDAY, GENDER ");
			sb.append( " FROM USERS ");
			sb.append( " WHERE USER_ID = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, 	user_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new UsersVO(
						rs.getString("USER_ID"),
						rs.getString("EMAIL"), 
						rs.getString("PASSWORD"), 
						rs.getString("PHONENUMBER"), 
						rs.getString("BIRTHDAY"), 
						rs.getString("GENDER")); 		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}

	//SELECT : 테이블 전체 데이터 조회
	public List<UsersVO> selectAll() {
		List<UsersVO> list = null;
		
		try {
			//DB연결 
			conn =  CommonJDBCUtil.getConnection();
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT USER_ID, EMAIL, PASSWORD, PHONENUMBER, BIRTHDAY, GENDER  ");
			sb.append("  FROM USERS ");
			sb.append(" ORDER BY USER_ID ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<UsersVO>();
			
			while (rs.next()) {
				UsersVO vo = new UsersVO(
						rs.getString("USER_ID"),
						rs.getString("EMAIL"), 
						rs.getString("PASSWORD"), 
						rs.getString("PHONENUMBER"), 
						rs.getString("BIRTHDAY"), 
						rs.getString("GENDER"));
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	//INSERT : VO 데이터를 받아서 입력
	public int insert (UsersVO vo) {
		int result = 0;
		
		try {
			conn =  CommonJDBCUtil.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO USERS ");
			sb.append("       (USER_ID, EMAIL, PASSWORD, PHONENUMBER, BIRTHDAY, GENDER)  ");
			sb.append("VALUES (?, ?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
		int i = 1;
		pstmt.setString(i++, vo.getUser_id());
		pstmt.setString(i++, vo.getEmail());
		pstmt.setString(i++, vo.getPassword());
		pstmt.setString(i++, vo.getPhonenumber());
		pstmt.setString(i++, vo.getBirthday());
		pstmt.setString(i++, vo.getGender());
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
		result = -1;
	} finally {
		CommonJDBCUtil.close(conn, pstmt);
	}
	
	return result;
}
	
	
	public int update (UsersVO vo) {
		int result = 0;
		
		try {
			conn =  CommonJDBCUtil.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE USERS ");
			sb.append("   SET EMAIL = ? ");
			sb.append("     , PASSWORD = ? ");
			sb.append("     , PHONENUMBER = ? ");
			sb.append("     , BIRTHDAY = ? ");
			sb.append("     , GENDER = ? ");
			sb.append(" WHERE USER_ID = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstmt.setString(i++, vo.getEmail());
			pstmt.setString(i++, vo.getPassword());
			pstmt.setString(i++, vo.getPhonenumber());
			pstmt.setString(i++, vo.getBirthday());
			pstmt.setString(i++, vo.getGender());
			pstmt.setString(i++, vo.getUser_id());
			
			result = pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
			result = -1;
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
		
		return result;
	}
	// DELETE : 키값(id)을 받아서 삭제
	public int delete (String user_id) {
		int count = 0;
		
		try {
			conn =  CommonJDBCUtil.getConnection();
			
			String sb = "DELETE FROM USERS WHERE USER_ID = ? ";
			pstmt = conn.prepareStatement(sb);
			
			pstmt.setString(1, user_id);
			
			count = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("[예외발생] " + e.getMessage() );
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
		
		return count;
	}
	
	
	
}
