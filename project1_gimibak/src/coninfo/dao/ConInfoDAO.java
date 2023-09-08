package coninfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coninfo.conInfo_vo.ConInfoVO;



public class ConInfoDAO {
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
            e.printStackTrace();
        }
    }
    // SELECTALL -----------------------------------------------------------
    public List<ConInfoVO> selectAll() {
        List<ConInfoVO> list = new ArrayList<>();

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2. SQL 문 실행
            String sql = "SELECT CONCERT_ID, TITLE, GENRE, RUNNING_TIME, CONCERT_DATE, LOCATION, TIME, HALL_ID FROM CONCERT_INFO ORDER BY CONCERT_ID";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // 3. 결과 처리
            while (rs.next()) {
                ConInfoVO vo = new ConInfoVO(
                        rs.getInt("Concert_id"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getInt("Running_time"),
                        rs.getString("Concert_date"),
                        rs.getString("Location"),
                        rs.getString("Time"),
                		rs.getInt("Hall_id"));
                list.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 자원 반납
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            close(conn, pstmt, rs);
        }

        return list;
    }
    // SELECTONE 추가 (28일(월))
    public ConInfoVO selectOne(String concertscanner) {
        ConInfoVO vo = null;

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2. SQL 문 실행
            String sql = "SELECT CONCERT_ID, TITLE, GENRE, RUNNING_TIME, CONCERT_DATE, LOCATION, TIME, HALL_ID FROM CONCERT_INFO WHERE CONCERT_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(concertscanner));

            // 3. 결과 처리
            rs = pstmt.executeQuery();

            if (rs.next()) {
                vo = new ConInfoVO(
                        rs.getInt("Concert_id"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getInt("Running_time"),
                        rs.getString("Concert_date"),
                        rs.getString("Location"),
                        rs.getString("Time"),
                		rs.getInt("Hall_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 자원 반납
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            close(conn, pstmt, rs);
        }

        return vo;
    }
    // INSERT -------------------------------------
    public int insert(ConInfoVO vo) {
        int result = 0;

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO CONCERT_INFO (CONCERT_ID, TITLE, GENRE, RUNNING_TIME, CONCERT_DATE, LOCATION, TIME, HALL_ID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, vo.getConcert_id());
            pstmt.setString(2, vo.getTitle());
            pstmt.setString(3, vo.getGenre());
            pstmt.setInt(4, vo.getRunning_time());
            pstmt.setString(5, vo.getConcert_date());
            pstmt.setString(6, vo.getLocation());
            pstmt.setString(7, vo.getTime());
            pstmt.setInt(8,  vo.getHall_id());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
            result = -1;
        } finally {
            close(conn, pstmt, rs);
        }

        return result;
    }
    
    // UPDATE ------------------------------------------
    public int update(ConInfoVO vo) {
    	int result = 0;
    	try {
			conn = DriverManager.getConnection(URL, USER,PASSWORD);
			
//	    	StringBuilder sql = new StringBuilder(); // 테이블 이름 수정
//	    	sql.append("UPDATE CONCERT_INFO ");
//	    	sql.append("   SET CONCERT_ID = ? ");
//	    	sql.append("   	 , TITLE = ? ");
//	    	sql.append("   	 , GENRE = ? ");
//	    	sql.append("   	 , RUNNING_TIME = ? ");
//	    	sql.append("CONCERT_DATE = TO_DATE(?, 'YYYY-MM-DD'), ");
//	    	sql.append("   	 , LOCATION = ? ");
//	    	sql.append("   	 , TIME = ? "); // 컬럼 이름 수정
//	    	sql.append("     , HALL_ID = ? "); // 테이블 수정해서 추가됨
//	    	sql.append("WHERE CONCERT_ID = ?");
	    	
			String sql = "UPDATE CONCERT_INFO "
					+ "SET TITLE = ?, "
                    + "GENRE = ?, "
                    + "RUNNING_TIME = ?, "
                    + "CONCERT_DATE = TO_DATE(?, 'YYYY-MM-DD'), "
                    + "LOCATION = ?, "
                    + "TIME = ?, "
                    + "HALL_ID = ? "
                    + "WHERE CONCERT_ID = ?"; // 추가된 부분
	    	
	    	pstmt = conn.prepareStatement(sql.toString());

	    	int i = 1;
            pstmt.setString(i++, vo.getTitle());
            pstmt.setString(i++, vo.getGenre());
            pstmt.setInt(i++, vo.getRunning_time());
            pstmt.setString(i++, vo.getConcert_date().substring(0,10));
            pstmt.setString(i++, vo.getLocation());
            pstmt.setString(i++, vo.getTime()); // 테이블 수정해서 추가됨
            pstmt.setInt(i++,  vo.getHall_id()); //추가된 부분
            pstmt.setInt(i++, vo.getConcert_id()); // 추가된 부분 (WHERE 조건)
	    

			result = pstmt.executeUpdate();
	    	
	    	
    	} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
			result = -1;
		} finally {
			close(conn, pstmt, rs);
		}
    	
		return result;
    }
    
    
	// DELETE ------------------------------------------
    public int delete(ConInfoVO vo) {
        int result = 0;

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "DELETE FROM CONCERT_INFO WHERE CONCERT_ID = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getConcert_id());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
            result = -1;
        } finally {
            close(conn, pstmt);
        }
        return result;
    }

    private void close(Connection conn, PreparedStatement pstmt) {
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {    
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn2, PreparedStatement pstmt2, ResultSet rs2) {
    	 try {
             if (pstmt != null) pstmt.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {    
             if (conn != null) conn.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
         try {
             if (rs != null) pstmt.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
        
	}
}