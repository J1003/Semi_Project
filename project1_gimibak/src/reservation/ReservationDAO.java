package reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.CommonJDBCUtil;

public class ReservationDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	// 전체 데이터 검색(찾기) - selectAll() : List<MemberVO>
	public List<ReservationVO> selectAll() {
		List<ReservationVO> list = null;	
		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT book_id, user_id, concert_id, hall_id, phonenumber, ");
			sb.append("count, seat, "
					+ "price, paymentMethod, howtoget, createDate, status");
			sb.append("  FROM RESERVATION ");
			sb.append(" ORDER BY BOOK_ID ");
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();

			list = new ArrayList<ReservationVO>();

			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			while (rs.next()) {
				ReservationVO vo = new ReservationVO(
						rs.getString("book_id"), 
						rs.getString("user_id"), 
						rs.getInt("concert_id"), 
						rs.getInt("hall_id"), 
						rs.getString("phonenumber"), 
						rs.getInt("count"),
						rs.getString("seat"),
						rs.getString("price"),
						rs.getString("paymentmethod"),
						rs.getString("howtoget"),
						rs.getString("createDate"),
						rs.getString("status"));
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//5. 클로징 처리에 의한 자원 반납
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}
	

	
	public ReservationVO selectOne(String id) {
		ReservationVO vo = null;
		
		//(할일) DB연결하고 SQL문 실행해서 결과값을 vo 변수에 저장하고 리턴
		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT book_id, user_id, concert_id, hall_id, phonenumber, "
					+ "count, seat, price, paymentMethod, howtoget, createDate, status ");
			sb.append("  FROM RESERVATION ");
			sb.append(" WHERE book_id = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			if (rs.next()) {
				vo = new ReservationVO(
						rs.getString("book_id"), 
						rs.getString("user_id"), 
						rs.getInt("concert_id"), 
						rs.getInt("hall_id"), 
						rs.getString("phonenumber"), 
						rs.getInt("count"),
						rs.getString("seat"),
						rs.getString("price"),
						rs.getString("paymentmethod"),
						rs.getString("howtoget"),
						rs.getString("createDate"),
						rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//5. 클로징 처리에 의한 자원 반납
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	//CommondJDBCUtil 사용 연결, close 처리
	//(실습) INSERT : VO 전달받아 데이터 입력처리 - insert(vo) : int
	public int insert(ReservationVO vo) {
		int result = 0;

		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO RESERVATION ");
			sql.append("(book_id, user_id, concert_id, hall_id, phonenumber, count, seat, "
					+ "price, paymentMethod, howtoget, createDate, status) ");
			sql.append("VALUES (?, "
					+ "(SELECT USER_ID FROM USERS  WHERE USER_ID = ?), "
					+ "(SELECT CONCERT_ID FROM CONCERT_INFO WHERE CONCERT_ID = ?), "
					+ "(SELECT HALL_ID FROM HALL_INFO WHERE HALL_ID = ?), "
					+ "?, ?, ?, ?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setString(i++, vo.getBook_id());
			pstmt.setString(i++, vo.getUser_id());
			pstmt.setInt(i++, vo.getConcert_id());
			pstmt.setInt(i++, vo.getHall_id());
			pstmt.setString(i++, vo.getPhonenumber());
			pstmt.setInt(i++, vo.getCount());
			pstmt.setString(i++, vo.getSeat());
			pstmt.setString(i++, vo.getPrice());
			pstmt.setString(i++, vo.getPaymentmethod());
			pstmt.setString(i++, vo.getHowtoget());
			pstmt.setString(i++, vo.getCreateDate());
			pstmt.setString(i++, vo.getStatus());
	
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());			
			result = -1;
		} finally {
			//5. 클로징 처리에 의한 자원 반납
			CommonJDBCUtil.close(conn, pstmt);
		}
		
		return result;
	}

	//UPDATE : VO 데이터를 받아서 수정 - update : int
		public int update(ReservationVO vo) {
			int result = 0;
			
			try {
				//2. DB연결 - Connection 객체 생성 <- DriverManager
				conn = CommonJDBCUtil.getConnection();
				
				//3. Statement 문 실행(SQL 문 실행)
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE RESERVATION ");
				sql.append("   SET user_id = ? ");
				sql.append("     , concert_id = ? ");
				sql.append("     , hall_id = ? ");
				sql.append("     , phonenumber = ? ");
				sql.append("     , count = ? ");
				sql.append("     , seat = ? ");
				sql.append("     , price = ? ");
				sql.append("     , paymentMethod = ? ");
				sql.append("     , howtoget = ? ");
				sql.append("     , createDate = ? ");
				sql.append("     , status = ? ");
				sql.append(" WHERE book_id = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				// ? 값 설정
//				pstmt.setString(1, vo.getName());
//				pstmt.setInt(2, vo.getKor());
//				pstmt.setInt(3, vo.getEng());
//				pstmt.setInt(4, vo.getMath());
//				pstmt.setInt(5, vo.getTot());
//				pstmt.setDouble(6, vo.getAvg());
//				pstmt.setString(7, vo.getId());
				
				int i = 1;
				
				pstmt.setString(i++, vo.getUser_id());
				pstmt.setInt(i++, vo.getConcert_id());
				pstmt.setInt(i++, vo.getHall_id());
				pstmt.setString(i++, vo.getPhonenumber());
				pstmt.setInt(i++, vo.getCount());
				pstmt.setString(i++, vo.getSeat());
				pstmt.setString(i++, vo.getPrice());
				pstmt.setString(i++, vo.getPaymentmethod());
				pstmt.setString(i++, vo.getHowtoget());
				pstmt.setString(i++, vo.getCreateDate());
				pstmt.setString(i++, vo.getStatus());
				pstmt.setString(i++, vo.getBook_id());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				//e.printStackTrace();
				System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
				result = -1;
			} finally {
				//5. 클로징 처리에 의한 자원 반납
				CommonJDBCUtil.close(conn, pstmt);
			}
			
			return result;
		}
		
	public int delete(String book_id) {
		int count = 0;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			
			String sql = "DELETE FROM RESERVATION WHERE BOOK_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, book_id);
			
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

