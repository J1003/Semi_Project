package users;

import java.util.List;
import java.util.Scanner;

import point.PointDAO;
import point.PointVO;
import qna.QnaDAO;
import qna.QnaVO;
import reservation.ReservationDAO;
import reservation.ReservationVO;


public class MyPage {
	private Scanner scan = new Scanner(System.in);
	
	
	public void mypage() {
	ReservationDAO reservationDAO = new ReservationDAO();
	List<ReservationVO> rlist = reservationDAO.selectAll();
	
	QnaDAO qnaDAO = new QnaDAO();
	List<QnaVO> qlist = qnaDAO.selectAll();
	
	//reservation 테이블 데이터 전체 조회
	// 1. 로그인했으면 바로 2번으로 아니면 1번에서 로그인시키기
	System.out.println();
	System.out.println("<로그인>");
	String useridscanner;
	
	int intscanner; //숫자 스캐너
	String sscanner; //문자열 스캐너
	
		while (true) {
			System.out.print("아이디 : ");
			useridscanner = scan.nextLine();

			System.out.print("비밀번호 : ");
			sscanner = scan.nextLine();
			
			//GKAJT 아이디
			//GKAJT 비밀번호
			UsersDAO usersDAO2 = new UsersDAO();
			UsersVO user = usersDAO2.selectOne(useridscanner);
			
			System.out.println();
			System.out.println("---------------------------------------------------------------");
			
			if (user != null && user.getUser_id().equalsIgnoreCase(useridscanner) &&
					user.getPassword().equalsIgnoreCase(sscanner)) { 
				// 로그인 성공
		        
		        
				System.out.println(useridscanner + "님 환영합니다.");
				System.out.println();
				System.out.println("---------------------------------------------------------------");

				while (true) {
					// 2. 원하는 메뉴 선택하기
					System.out.println("1.예매내역 조회 2.문의내역 조회 3.내 포인트 4.내 정보 5.처음으로 돌아가기");
					System.out.println("---------------------------------------------------------------");
					System.out.print("번호 입력 : ");
					intscanner = scan.nextInt();
					scan.nextLine();
					System.out.println();
					System.out.println("---------------------------------------------------------------");
					

					// 2-1. 예매내역 조회
					if (intscanner == 1) {
						System.out.println("<" + useridscanner + "님의 예매내역>");
						System.out.println("---------------------------------------------------------------");
						boolean hasReservation = false; // 예약 내역이 있는지 여부를 저장하는 변수
						 System.out.println("예약ID\t\t사용자ID\t콘서트ID\t홀ID\t연락처\t\t예매인원\t좌석번호\t좌석가격\t수령방법\t예약날짜\t\t상태");
							for (ReservationVO vo : rlist) {
								if (vo.getUser_id() != null && vo.getUser_id().equalsIgnoreCase(useridscanner)) {
								 System.out.println(String.format("%-1s\t%-7s\t%-1s\t%-5s\t%-15s\t%-1s\t%-1s\t%-1s\t%-1s"
								 		+ "\t%-1s\t%-51s",
					                      vo.getBook_id(), vo.getUser_id(), vo.getConcert_id(),
					                      vo.getHall_id(), vo.getPhonenumber(), vo.getCount(),
					                      vo.getSeat(), vo.getPrice(), vo.getHowtoget(), 
					                      vo.getCreateDate(), vo.getStatus()));
								 hasReservation = true; // 예약 내역이 있는 경우 true로 설정
								}
							}
							if (!hasReservation) {
							    System.out.println(useridscanner + "님의 예매내역이 없습니다.");
							}
						System.out.println();
						System.out.println("---------------------------------------------------------------");
					}
					
					// 2-2. 문의내역 조회
					if (intscanner == 2) {
						System.out.println(useridscanner + "님의 문의내역 입니다.");
			            System.out.println("번호\t질문내용\t\t\t\t진행상황");
						for (QnaVO vo : qlist) {
							if (vo.getUser_id() != null && vo.getUser_id().equalsIgnoreCase(useridscanner)) {
								System.out.println(String.format("%-1s\t%-30s\t%-10s",
					                      vo.getQna_id(), vo.getQuestion(), vo.getStatus()));
						    }
						}
						System.out.println();

					}
					
					// 포인트 조회
					if (intscanner == 3) {
						PointDAO pointDao = new PointDAO();

				        // 사용자의 포인트 정보 조회
				        PointVO pointVO = pointDao.selectOne(useridscanner);
				        if (pointVO != null) {
				            System.out.println("사용자 아이디: " + pointVO.getUser_id());
				            System.out.println("포인트: " + pointVO.getPoint());
				            System.out.println("발급 날짜: " + pointVO.getIssue_date());
				            System.out.println("만료 날짜: " + pointVO.getExpiry_date());
				            System.out.println("메모: " + (pointVO.getMemo() != null ? pointVO.getMemo() : "없음"));
				        } else {
				            System.out.println("해당 아이디의 포인트 정보가 없습니다.");
				        }
					}
					System.out.println("---------------------------------------------------------------");
					if (intscanner == 4) {
//						
						System.out.println("원하는 서비스의 번호를 입력하시오.");
						System.out.println("1.회원 정보 수정 2.회원 탈퇴");
						System.out.println("---------------------------------------------------------------");
						System.out.print("번호 입력 : ");
						System.out.println("\n");
						System.out.println("---------------------------------------------------------------");
						int intsc = scan.nextInt();
						scan.nextLine();
						if(intsc == 1) {
							UsersDAO usersDAO = new UsersDAO();
					         
			                System.out.println("현재 회원 정보:");
			                System.out.println("아이디: " + user.getUser_id());
			                System.out.println("이메일: " + user.getEmail());
			                System.out.println("전화번호: " + user.getPhonenumber());
			                System.out.println("생년월일: " + user.getBirthday());
			                System.out.println("성별: " + user.getGender());
			                System.out.println();
							System.out.println("---------------------------------------------------------------");
			                
			                System.out.println("어떤 항목을 수정하시겠습니까?");
			                System.out.print("1. 이메일 2. 전화번호 3. 생년월일 4. 성별 5. 취소");
			                System.out.print("번호 입력 : ");
							int choice = Integer.parseInt(scan.nextLine());
							System.out.println();
							System.out.println("---------------------------------------------------------------");
			              
			                
			                

			                switch (choice) {
			                    case 1:
			                        System.out.print("새로운 이메일을 입력하세요: ");
			                        String newEmail = scan.nextLine();
			                        user.setEmail(newEmail);
			                        break;
			                    case 2:
			                        System.out.print("새로운 전화번호를 입력하세요(000-0000-0000): ");
			                        String newPhoneNumber = scan.nextLine();
			                        user.setPhonenumber(newPhoneNumber);
			                        break;
			                    case 3:
			                        System.out.print("새로운 생년월일(숫자 8자리 ex) 19450815)을 입력하세요: ");
			                        String newBirthday = scan.nextLine();
			                        user.setBirthday(newBirthday);
			                        break;
			                    case 4:
			                        System.out.print("새로운 성별(M/F)을 입력하세요: ");
			                        String newGender = scan.nextLine();
			                        user.setGender(newGender);
			                        break;
			                    case 5:
			                        System.out.println("수정을 취소합니다.");
			                        return;
			                    default:
			                        System.out.println("잘못된 선택입니다.");
			                        return;
			                }

			                int result = usersDAO.update(user);

			                if (result > 0) {
			                    System.out.println("회원 정보가 성공적으로 수정되었습니다.");
			                    break;
			                } else {
			                    System.out.println("회원 정보 수정에 실패하였습니다.");
			                }
			             
			                System.out.println();
			                System.out.println("---------------------------------------------------------------");
							
						} 
						if(intsc == 2) {
							UsersDAO usersDAO = new UsersDAO();

					    	if (user != null) {
					    	    System.out.print("비밀번호를 입력하세요: ");
					    	    String password = scan.nextLine();
					    	    
					    	    if (user.getPassword().equals(password)) {
					    	        int result = usersDAO.delete(useridscanner);
					    	        
					    	        if (result > 0) {
					    	            System.out.println("회원 정보가 성공적으로 삭제되었습니다.");
					    	        } else {
					    	            System.out.println("회원 정보 삭제에 실패하였습니다.");
					    	        }
					    	    } else {
					    	        System.out.println("비밀번호가 일치하지 않습니다. 회원 정보 삭제를 취소합니다.");
					    	    }
					    	} 
					}
					
					// 처음으로 돌아가기
					if (intscanner == 5) {
						System.out.println();
						break;
					} 
				}
				}
			}else {
            // 올바르지 않은 비밀번호를 입력한 경우
            System.out.println("아이디 또는 비밀번호가 올바르지 않습니다. 다시 입력하세요.");
			}
		} 
	}
}


