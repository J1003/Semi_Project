package seat_info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.CommonJDBCUtil;
import hall_info.Hall_infoVO;

public class Seat_infoDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	// 전체 데이터 검색(찾기) - selectAll() : List<MemberVO>
	public List<Seat_infoVO> selectAll() {
		List<Seat_infoVO> list = null;	
		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sb = new StringBuilder();
			
			sb.append("SELECT seat_id, hall_id, seat_no, seat_price, hallname");
			sb.append("  FROM SEAT_INFO ");
			sb.append(" ORDER BY SEAT_ID ");
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Seat_infoVO>();
			
			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			while (rs.next()) {
				Seat_infoVO vo = new Seat_infoVO(
						rs.getInt("seat_id"), 
						rs.getInt("hall_id"), 
						rs.getString("seat_no"), 
						rs.getString("seat_price"),
						rs.getString("hallname"));
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
	
	public Seat_infoVO selectOne(String id) {
		Seat_infoVO vo = null;
		
		//(할일) DB연결하고 SQL문 실행해서 결과값을 vo 변수에 저장하고 리턴
		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			

			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT seat_id, hall_id, seat_no, seat_price, hallname");
			sb.append("  FROM SEAT_INFO ");
			sb.append(" WHERE seat_id = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			if (rs.next()) {
				vo = new Seat_infoVO(
						rs.getInt("seat_id"), 
						rs.getInt("hall_id"), 
						rs.getString("seat_no"), 
						rs.getString("seat_price"),
						rs.getString("hallname"));
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
	public int insert(Seat_infoVO vo) {
		int result = 0;

		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO SEAT_INFO ");
			sql.append("(seat_id, hall_id, seat_no, seat_price, hallname) ");
			sql.append("SELECT ?, h.hall_id, ?, ?, h.name FROM hall_info WHERE hall_id = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setInt(i++, vo.getSeat_id());
			pstmt.setString(i++, vo.getSeat_no());
			pstmt.setString(i++, vo.getSeat_price());
			pstmt.setInt(i++, vo.getHall_id());
			
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
		public int update(Seat_infoVO vo) {
			int result = 0;
			
			try {
				//2. DB연결 - Connection 객체 생성 <- DriverManager
				conn = CommonJDBCUtil.getConnection();
				
				//3. Statement 문 실행(SQL 문 실행)
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE SEAT_INFO ");
				sql.append("   SET seat_no = ? ");
				sql.append("     , seat_price = ? ");
				sql.append(" WHERE seat_id = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				int i = 1;
				
				pstmt.setString(i++, vo.getSeat_no());
				pstmt.setString(i++, vo.getSeat_price());
				pstmt.setInt(i++, vo.getSeat_id());

				
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
		
		
	public int delete(int seat_id) {
		int count = 0;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			
			String sql = "DELETE FROM SEAT_INFO WHERE SEAT_ID = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, seat_id);
			
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

