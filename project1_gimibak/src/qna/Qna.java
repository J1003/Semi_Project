package qna;

import java.util.List;
import java.util.Scanner;

import users.UsersDAO;
import users.UsersVO;

public class Qna {
    public void qnaStart() {
        Scanner scan = new Scanner(System.in);
  
        QnaDAO qnaDAO = new QnaDAO(); 
		List<QnaVO> qlist = qnaDAO.selectAll(); 
		
		UsersDAO usersDAO = new UsersDAO(); 
		List<UsersVO> ulist = usersDAO.selectAll(); 
			
		
    	int intscanner; //숫자 스캐너
    	String sscanner; //문자열 스캐너
    
    	int qna_id = 0;
		String user_id = null;
    	String email = null;
    	String question = null;
    	String status = null;
    	
    	String idscanner = null; 
    	String emailscanner = null; 
    	
    	UsersVO id = null;
    	
    	while (true) {
    	System.out.println("<Q&A>");
		System.out.println("1.회원 2.비회원");
		while (true) {
			System.out.print("번호 입력 : ");
			intscanner = scan.nextInt();
			scan.nextLine();
			System.out.println();
		
			if (intscanner == 1) {
				System.out.println("아이디를 입력하세요.");
				while (true) {
					System.out.print("아이디 : ");
					idscanner = scan.nextLine();

					id = usersDAO.selectOne(String.valueOf(idscanner));
		            if (id != null) {
		                break; 
		            } else {
		                System.out.println("잘못된 입력입니다. 다시 입력하세요.");
		            }
		        }
		    	email = id.getEmail();
				user_id = id.getUser_id();
		        break; // 최외부 루프 종료
			} else if (intscanner == 2) {
				System.out.println("답변 받으실 이메일을 입력하세요.");
				System.out.print("이메일 : ");
				emailscanner = scan.nextLine();
		    	email = emailscanner;
				user_id = null;
				break;
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
		
		}		
		
		System.out.println("문의하실 내용을 200자 이내로 입력하세요.");
		System.out.print("문의 내용 : ");
		sscanner = scan.nextLine();
		System.out.println();
		
		int maxQnaId = 0;

		for (QnaVO existQnaId : qnaDAO.selectAll()) {
		    if (existQnaId.getQna_id() > maxQnaId) {
		    	maxQnaId = existQnaId.getQna_id();
		    }
		} 
		
		qna_id = maxQnaId + 1;
    	question = sscanner;
    	status = null;
					
		QnaVO newqna = new QnaVO (qna_id, user_id, email, question, status);
		qnaDAO.insert(newqna);
						
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


