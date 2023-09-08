package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.CommonJDBCUtil;

public class QnaDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	// 전체 데이터 검색(찾기) - selectAll() : List<MemberVO>
	public List<QnaVO> selectAll() {
		List<QnaVO> list = null;	
		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT qna_id, user_id, email, question, status ");
			sb.append("  FROM qna ");
			sb.append(" ORDER BY qna_id ");
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();

			list = new ArrayList<QnaVO>();

			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			while (rs.next()) {
				QnaVO vo = new QnaVO(
						rs.getInt("qna_id"), 
						rs.getString("user_id"), 
						rs.getString("email"), 
						rs.getString("question"), 
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
	

	
	public QnaVO selectOne(String id) {
		QnaVO vo = null;
		
		//(할일) DB연결하고 SQL문 실행해서 결과값을 vo 변수에 저장하고 리턴
		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT qna_id, user_id, email, question, status ");
			sb.append("  FROM qna ");
			sb.append(" WHERE qna_id = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			//4. SQL 실행 결과에 대한 처리
			//   - SELECT : 조회(검색) 데이터 결과 값에 대한 처리
			if (rs.next()) {
				vo = new QnaVO(
						rs.getInt("qna_id"), 
						rs.getString("user_id"), 
						rs.getString("email"), 
						rs.getString("question"), 
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
	public int insert(QnaVO vo) {
		int result = 0;

		
		try {
			//2. DB연결 - Connection 객체 생성 <- DriverManager
			conn = CommonJDBCUtil.getConnection();
			
			//3. Statement 문 실행(SQL 문 실행)
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO qna ");
			sql.append("(qna_id, user_id, email, question, status) ");
			sql.append("VALUES (?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sql.toString());
			
			int i = 1;
			pstmt.setInt(i++, vo.getQna_id());
			pstmt.setString(i++, vo.getUser_id());
			pstmt.setString(i++, vo.getEmail());
			pstmt.setString(i++, vo.getQuestion());
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
		public int update(QnaVO vo) {
			int result = 0;
			
			try {
				//2. DB연결 - Connection 객체 생성 <- DriverManager
				conn = CommonJDBCUtil.getConnection();
				
				//3. Statement 문 실행(SQL 문 실행)
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE qna ");
				sql.append("   SET user_id = ? ");
				sql.append("     , email = ? ");
				sql.append("     , answer = ? ");
				sql.append(" WHERE qna_id = ? ");
				
				pstmt = conn.prepareStatement(sql.toString());
				
				int i = 1;
				
				pstmt.setString(i++, vo.getUser_id());
				pstmt.setString(i++, vo.getEmail());
				pstmt.setString(i++, vo.getQuestion());
				pstmt.setString(i++, vo.getStatus());
				pstmt.setInt(i++, vo.getQna_id());
				
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
		
	public int delete(int _id) {
		int count = 0;
		
		try {
			conn = CommonJDBCUtil.getConnection();
			
			String sql = "DELETE FROM qna WHERE _id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, _id);
			
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

