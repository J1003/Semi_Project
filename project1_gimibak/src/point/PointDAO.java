package point;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import common.CommonJDBCUtil;

public class PointDAO {
	
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
	public PointVO selectOne(String user_id) {
		PointVO vo = null;
		
		try {
			conn =  CommonJDBCUtil.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT POI_ID, USER_ID, POINT, ISSUE_DATE, EXPIRY_DATE, MEMO ");
			sb.append( " FROM POINT ");
			sb.append( " WHERE USER_ID = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = new PointVO(
						rs.getInt("POI_ID"),
						rs.getString("USER_ID"), 
						rs.getInt("POINT"), 
						rs.getString("ISSUE_DATE"), 
						rs.getString("EXPIRY_DATE"), 
						rs.getString("MEMO")); 		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}

	//SELECT : 테이블 전체 데이터 조회
	public List<PointVO> selectAll() {
		List<PointVO> list = null;
		
		try {
			//DB연결 
			conn =  CommonJDBCUtil.getConnection();
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT POI_ID, USER_ID, POINT, ISSUE_DATE, EXPIRY_DATE, MEMO ");
			sb.append("  FROM POINT ");
			sb.append(" ORDER BY POI_ID ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<PointVO>();
			
			while (rs.next()) {
				PointVO vo = new PointVO(
						rs.getInt("POI_ID"),
						rs.getString("USER_ID"), 
						rs.getInt("POINT"), 
						rs.getString("ISSUE_DATE"), 
						rs.getString("EXPIRY_DATE"), 
						rs.getString("MEMO")); 		
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CommonJDBCUtil.close(conn, pstmt, rs);
		}
		return list;
	}
	//INSERT : 오류 발생 또는 이벤트 등 관리자가 개입해야하는 경우
	public int insert (PointVO vo) {
		int result = 0;
		
		try {
			conn =  CommonJDBCUtil.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO POINT ");
			sb.append("       (POI_ID, USER_ID, POINT, ISSUE_DATE, EXPIRY_DATE, MEMO)  ");
			sb.append("VALUES (?, ?, ?, ?, ?, ?) ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
		int i = 1;
		pstmt.setInt(i++, vo.getPoi_id());
		pstmt.setString(i++, vo.getUser_id());
		pstmt.setInt(i++, vo.getPoint());
		pstmt.setString(i++, vo.getIssue_date());
		pstmt.setString(i++, vo.getExpiry_date());
		pstmt.setString(i++, vo.getMemo());
		
		result = pstmt.executeUpdate();
	} catch (SQLException e) {
		System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
		result = -1;
	} finally {
		CommonJDBCUtil.close(conn, pstmt);
	}
	
	return result;
}
	// 구매 가격의 5%를 포인트로 적립하는 메서드
    public int accumulatePoints(String user_id, int purchaseAmount) {
        int accumulatedPoints = (int) (purchaseAmount * 0.05); // 5%를 계산하여 적립할 포인트 계산

        try {
            conn = CommonJDBCUtil.getConnection();

            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO POINT ");
            sb.append("(POI_ID, USER_ID, POINT, ISSUE_DATE, EXPIRY_DATE, MEMO) ");
            sb.append("VALUES (POINT_SEQ.NEXTVAL, ?, ?, TO_CHAR(SYSDATE, 'YYYYMMDD'), TO_CHAR(ADD_MONTHS(SYSDATE, 60), 'YYYYMMDD'), ?) ");

            pstmt = conn.prepareStatement(sb.toString());

            int i = 1;
            pstmt.setString(i++, user_id);
            pstmt.setInt(i++, accumulatedPoints);
            pstmt.setString(i++, "적립");

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
            return -1;
        } finally {
            CommonJDBCUtil.close(conn, pstmt);
        }
    }
	
	public int update (PointVO vo) {
		int result = 0;
		
		try {
			conn =  CommonJDBCUtil.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE POINT ");
			sb.append("   SET POI_ID = ? ");
			sb.append("     , USER_ID = ? ");
			sb.append("     , POINT = ? ");
			sb.append("     , ISSUE_DATE = ? ");
			sb.append("     , EXPIRY_DATE = ? ");
			sb.append("     , MEMO = ? ");
			sb.append(" WHERE POI_ID = ? ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			int i = 1;
			pstmt.setInt(i++, vo.getPoi_id());
			pstmt.setString(i++, vo.getUser_id());
			pstmt.setInt(i++, vo.getPoint());
			pstmt.setString(i++, vo.getIssue_date());
			pstmt.setString(i++, vo.getExpiry_date());
			pstmt.setString(i++, vo.getMemo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
			result = -1;
		} finally {
			CommonJDBCUtil.close(conn, pstmt);
		}
		
		return result;
	}
	// 포인트를 차감하는 메서드
    public int usePoints(String user_id, int usedPoints) {
        try {
            conn = CommonJDBCUtil.getConnection();

            // 현재 보유한 포인트 조회
            PointVO currentPointVO = selectOne(user_id);
            if (currentPointVO == null) {
                System.out.println("해당 사용자의 포인트 정보가 없습니다.");
                return 0; // 포인트 차감 실패
            }

            int currentPoints = currentPointVO.getPoint();

            if (currentPoints < usedPoints) {
                System.out.println("보유한 포인트가 부족합니다.");
                return 0; // 포인트 차감 실패
            }

            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE POINT ");
            sb.append("SET POINT = POINT - ? ");
            sb.append("WHERE USER_ID = ? ");

            pstmt = conn.prepareStatement(sb.toString());

            int i = 1;
            pstmt.setInt(i++, usedPoints);
            pstmt.setString(i++, user_id);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("[예외발생] 작업중 예외가 발생 : " + e.getMessage());
            return -1;
        } finally {
            CommonJDBCUtil.close(conn, pstmt);
        }
    }
	// DELETE : 키값(id)을 받아서 삭제
	public int delete (String user_id) {
		int count = 0;
		
		try {
			conn =  CommonJDBCUtil.getConnection();
			
			String sb = "DELETE FROM POINT WHERE POI_ID = ? ";
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
