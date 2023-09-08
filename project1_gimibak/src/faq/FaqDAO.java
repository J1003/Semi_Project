package faq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.CommonJDBCUtil;

public class FaqDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	// 전체 데이터 검색(찾기) - selectAll() : List<MemberVO>
	public List<FaqVO> selectAll() {
		List<FaqVO> list = null;	
		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT faq_id, question, answer ");
			sb.append("  FROM Faq ");
			sb.append(" ORDER BY faq_id ");
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();

			list = new ArrayList<FaqVO>();

			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			while (rs.next()) {
				FaqVO vo = new FaqVO(
						rs.getInt("faq_id"), 
						rs.getString("question"), 
						rs.getString("answer")); 
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
	

	
	public FaqVO selectOne(String id) {
		FaqVO vo = null;
		
		//(할일) DB연결하고 SQL문 실행해서 결과값을 vo 변수에 저장하고 리턴
		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT faq_id, question, answer ");
			sb.append("  FROM Faq ");
			sb.append(" WHERE faq_id = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			if (rs.next()) {
				vo = new FaqVO(
						rs.getInt("faq_id"), 
						rs.getString("question"), 
						rs.getString("answer"));
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
	public int insert(FaqVO vo) {
		int result = 0;

		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO Faq ");
			sql.append("(faq_id, question, answer) ");
			sql.append("VALUES (?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setInt(i++, vo.getFaq_id());
			pstmt.setString(i++, vo.getQuestion());
			pstmt.setString(i++, vo.getAnswer());
			
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
		public int update(FaqVO vo) {
			int result = 0;
			
			try {
				//2. DB연결 - Connection 객체 생성 <- DriverManager
				conn = CommonJDBCUtil.getConnection();
				
				//3. Statement 문 실행(SQL 문 실행)
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE Faq ");
				sql.append("   SET question = ? ");
				sql.append("     , answer = ? ");
				sql.append(" WHERE faq_id = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				int i = 1;
				
				pstmt.setString(i++, vo.getQuestion());
				pstmt.setString(i++, vo.getAnswer());
				pstmt.setInt(i++, vo.getFaq_id());
				
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
		
	public int delete(int faq_id) {
		int count = 0;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			
			String sql = "DELETE FROM Faq WHERE faq_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, faq_id);
			
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

