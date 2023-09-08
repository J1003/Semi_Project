package point;

import java.util.Scanner;

public class selectPoint {
	public void selectpoint() {
		PointDAO pointDao = new PointDAO();

        Scanner scanner = new Scanner(System.in);

        System.out.print("아이디를 입력하세요: ");
        String user_id = scanner.nextLine();

        // 사용자의 포인트 정보 조회
        PointVO pointVO = pointDao.selectOne(user_id);
        if (pointVO != null) {
            System.out.println("PointVO: " + pointVO);
        } else {
            System.out.println("해당 아이디의 포인트 정보가 없습니다.");
        }

        scanner.close();
	}
}
