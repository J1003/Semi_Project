package pay.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import pay.vo.Pay_VO;

public class PayTEST {
    public static void main(String[] args) {
        PayDao payDao = new PayDao();
        
        // SELECTALL
        List<Pay_VO> payments = payDao.selectAll();
        for (Pay_VO payment : payments) {
            System.out.println(payment);
        }
        
        System.out.println();
        
        
        // INSERT
        String userId = "GKAJT"; // 실제 사용자 ID로 변경
        String paymentMethod = "카드결제";
        int totalPrice = 50000;
        String paymentDate = generateRandomDate().toString();
        String bookId = "5001";

        Pay_VO newPayment = new Pay_VO(0, userId, paymentMethod, totalPrice, paymentDate, bookId);
        int result = payDao.insert(newPayment);
        if (result > 0) {
            System.out.println("삽입 성공");
        } else {
            System.out.println("삽입 실패");
        }
    
    
        // UPDATE
        int updatedTotalPrice = 55000;
        newPayment.setTotal_price(updatedTotalPrice); // 가격 변경
        
        // 여기서 아이디를 입력하세요
        int paymentIdToUpdate = 1; // 수정할 결제 정보의 아이디를 입력하세요
        
        newPayment.setPayment_id(paymentIdToUpdate); // 수정할 결제 정보의 아이디 설정
        result = payDao.update(newPayment); // update 메서드로 수정
        if (result > 0) {
            System.out.println("업데이트 성공");
        } else {
            System.out.println("업데이트 실패");
        }

        // DELETE
        result = payDao.delete(1); // 결제 ID를 매개변수로 수정
        if (result > 0) {
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }


    }
    private static LocalDate generateRandomDate() {
        // 8월 1일부터 8월 30일 사이에서 랜덤한 날짜 생성
        LocalDate startDate = LocalDate.of(2023, 8, 1);
        LocalDate endDate = LocalDate.of(2023, 8, 30);

        long startDay = startDate.toEpochDay();
        long endDay = endDate.toEpochDay();
        
        long randomDay = ThreadLocalRandom.current().nextLong(startDay, endDay + 1);
        return LocalDate.ofEpochDay(randomDay);
    }
}
