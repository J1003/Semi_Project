package point;

import users.UsersDAO;
import users.UsersVO;

public class AccumulatePoints {
	public void increasePoint () {
        UsersDAO userDao = new UsersDAO();
        PointDAO pointDao = new PointDAO();

        String user_id = "GKAJT";

        // 사용자 정보 조회
        UsersVO user = userDao.selectOne(user_id);
        System.out.println("User: " + user);

        // 포인트 적립
        int purchasedAmount = 10000; // 구매 가격
        int accumulatedPoints = purchasedAmount / 20; // 5% 적립
        int result = pointDao.accumulatePoints(user_id, accumulatedPoints);
        if (result > 0) {
            System.out.println(accumulatedPoints + " 포인트가 적립되었습니다.");
        } else {
            System.out.println("포인트 적립에 실패했습니다.");
        }
	}
}
