package faq;

import java.util.List;
import java.util.Scanner;

public class Faq {
    public void faqStart() {
        Scanner scan = new Scanner(System.in);
  
        FaqDAO faqDAO = new FaqDAO(); 
    	List<FaqVO> flist = faqDAO.selectAll(); 
    	
    	int intscanner; //숫자 스캐너
    	String sscanner; //문자열 스캐너
    

	while (true) {
		int faq_id = 0;
    	String question = null;
    	String answer = null;
 
    	int seatscanner; //예매하기 - 예매할 좌석 수 값
    	String paymentMethodscanner; //예매하기 - 결제방법 번호 입력 값
    	String phonescanner = null;
    	
		// 1. 
		System.out.println("<FAQ>");
		for (FaqVO vo : flist) {
		    System.out.println(vo.getFaq_id() + "." + vo.getQuestion());
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("원하는 질문의 번호를 입력하세요.");
		System.out.print("번호 입력 : ");
		intscanner = scan.nextInt();
		scan.nextLine();
		System.out.println();
		
		if (intscanner == 1) {
			FaqVO id = faqDAO.selectOne(String.valueOf(intscanner));
			System.out.println("Q : " + id.getQuestion());
			System.out.println("A : " + id.getAnswer());

		}
		System.out.println();
		System.out.println("---------------------------------------------------------------");

		
		
				
		System.out.println("문의사항이 더 있으시면 \"Y\"를 처음으로 돌아가시려면 \"N\"를 입력하세요.");
		while (true) {
			System.out.print("입력 : ");
			sscanner = scan.nextLine();
			if (sscanner.equalsIgnoreCase("Y")) {
				//여기서 Y 누르면 다시 예매 탭으로 가고
				System.out.println("---------------------------------------------------------------");
				break;
			} else if (sscanner.equalsIgnoreCase("N")) {
				//N 누르면 처음으로 돌아가게 하기
				break;
			} else {
            // Y나 N이 아닌 다른 입력인 경우
            System.out.println("잘못된 입력입니다. \"Y\"나 \"N\"을 입력하세요.");
			}
		}
		if (sscanner.equalsIgnoreCase("N")) {
			//N 누르면 처음으로 돌아가게 하기
			System.out.println("---------------------------------------------------------------");
			System.out.println();
			break;
		} 
	}
}
}

