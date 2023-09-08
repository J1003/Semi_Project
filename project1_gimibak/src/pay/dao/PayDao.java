package pay.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pay.vo.Pay_VO;

public class PayDao {

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
    
    // CommonJDBCUtil 클래스 대신 직접 JDBC 연결 및 클로징처리하는 방법
	// SELECT -----------------------------------------------------------
    public List<Pay_VO> selectAll() {
    	List<Pay_VO> list = new ArrayList<>();

        try {
            // 1. DB 연결
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2. SQL 문 실행
            String sql = "SELECT PAYMENT_ID, USER_ID, PAYMENT_METHOD, TOTAL_PRICE, PAYMENT_DATE, BOOK_ID FROM PAYMENT";
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            
            // 3. 결과 처리
            while (rs.next()) {
            	Pay_VO vo = new Pay_VO(
                        rs.getInt("PAYMENT_ID"),
                        rs.getString("USER_ID"),
                        rs.getString("PAYMENT_METHOD"),
                        rs.getInt("TOTAL_PRICE"),
                        rs.getString("PAYMENT_DATE"),
                        rs.getString("BOOK_ID"));
                list.add(vo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 4. 자원 반납
            close();
        }

        return list;
    }
    
    // INSERT -------------------------------------------------------------
    public int insert(Pay_VO vo) {
    	int result = 0;
    	
    	try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "INSERT INTO PAYMENT (PAYMENT_ID, USER_ID, PAYMENT_METHOD, TOTAL_PRICE, PAYMENT_DATE, BOOK_ID) "
			           + "VALUES (?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)";
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, vo.getPayment_id());
			pstmt.setString(2, vo.getUser_id());
			pstmt.setString(3, vo.getPayment_method());
			pstmt.setInt(4, vo.getTotal_price());
			pstmt.setString(5, vo.getPayment_date());
			pstmt.setString(6, vo.getBook_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
			result = -1;
		}
		return result;
    }
    
    // UPDATE -----------------------------------------------------------------
    public int update(Pay_VO vo) {
    	int result = 0;
    	
    	try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE PAYMENT ");
			sql.append("   SET PAYMENT_ID = ? ");
			sql.append("     , USER_ID = ? ");
			sql.append("     , PAYMENT_METHOD = ? ");
			sql.append("     , TOTAL_PRICE = ? ");
			sql.append("     , PAYMENT_DATE = ? ");
			sql.append("     , BOOK_ID = ? ");
			sql.append(" WHERE PAYMENT_ID = ? ");
			
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, vo.getPayment_id());
			pstmt.setString(2, vo.getUser_id());
			pstmt.setString(3, vo.getPayment_method());
			pstmt.setInt(4, vo.getTotal_price());
			pstmt.setString(5, vo.getPayment_date());
			pstmt.setString(6, vo.getBook_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
    }
    
    // DELETE ------------------------------------------------------------------
    public int delete(int paymentId) {
    	int result = 0;
    	
    	try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			String sql = "DELETE FROM PAYMENT WHERE PAYMENT_ID = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paymentId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
            result = -1;
        } finally {
            close();
        }

        return result;
    }

    private void close() {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }
}
