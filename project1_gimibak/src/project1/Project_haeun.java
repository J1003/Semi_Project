package project1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import coninfo.conInfo_vo.ConInfoVO;
import coninfo.dao.ConInfoDAO;
import faq.Faq;
import hall_info.Hall_infoDAO;
import hall_info.Hall_infoVO;
import pay.dao.PayDao;
import pay.vo.Pay_VO;
import point.PointDAO;
import point.PointVO;
import qna.Qna;
import reservation.Reservation;
import reservation.ReservationDAO;
import reservation.ReservationVO;
import seat_info.Seat_infoDAO;
import seat_info.Seat_infoVO;
import users.MyPage;
import users.PageSignup;
import users.UsersDAO;
import users.UsersVO;

public class Project_haeun {

	// 필드-------------------------------------------------------------
	private Scanner scan = new Scanner(System.in); // 스캐너 선언
	private static final String CONCERT_INFO = "CONCERT";
	private static final String RESERVATION = "예약하기";
	private static final String MY = "마이페이지";
	private static final String QUESTION = "문의사항";
	private static final String LOGIN = "로그인";
	private static final String SIGNUP = "회원가입";
	private static final String MANAGER = "관리자모드";

	private static final String MANAGERPW = "project1"; // 관리자모드 패스워드

	private static final LocalDate nowDate = LocalDate.now(); // 현재 날짜, 시간 입력할 때 사용할 변수

	// 각 메뉴 타입 객체 생성 for 메뉴 기능 메소드 실행-------------------------------------------------
	private Reservation booking = new Reservation();
	private MyPage mp = new MyPage();
	private Faq faq = new Faq();
	private Qna qna = new Qna();
	private PageSignup ps1 = new PageSignup();
	private PageSignup ps2 = new PageSignup();

	// DAO 타입 객체 생성 for CRUD 메소드 실행--------------------------------------------------
	public ReservationDAO reservationDAO = new ReservationDAO(); // reservation 테이블 용 메소드
	public ConInfoDAO conInfoDAO = new ConInfoDAO(); // concert_info 테이블 용 메소드
	public UsersDAO usersDAO = new UsersDAO(); // users 테이블 용 메소드
	public Hall_infoDAO hallInfoDAO = new Hall_infoDAO(); // users 테이블 용 메소드
	public Seat_infoDAO seatInfoDAO = new Seat_infoDAO(); // users 테이블 용 메소드

	// List<-VO> 타입 객체 생성 각 테이블의 저체 데이터 조회하기--------------------------------------------------
	public List<ReservationVO> rlist = reservationDAO.selectAll(); // reservation 테이블 데이터 전체 조회
	public List<ConInfoVO> clist = conInfoDAO.selectAll(); // concert_info 테이블 데이터 전체 조회
	public List<UsersVO> ulist = usersDAO.selectAll(); // users 테이블 데이터 전체 조회
	public List<Hall_infoVO> hlist = hallInfoDAO.selectAll(); // hall_info 테이블 데이터 전체 조회
	public List<Seat_infoVO> slist = seatInfoDAO.selectAll(); // seat_info 테이블 데이터 전체 조회

	private int intscanner = 0; // 숫자 스캐너
	private String sscanner = null; // 문자열 스캐너
	private int seatscanner = 0; // 좌석 선택용 스캐너

	private int select = 0; // 메인 메뉴에서 입력한 번호 값

	// 생성자------------------------------------------------------------
	// ???

	// 메소드------------------------------------------------------------
	public void startProject1() {
		while (true) {
			System.out.println("----------------------------GIMIBAK----------------------------");
			System.out.println("1." + CONCERT_INFO + " 2." + RESERVATION + " 3." + MY + " 4." + QUESTION + " 5." + LOGIN
					+ " 6." + SIGNUP + " 7." + MANAGER + " 0.종료");
			System.out.println("---------------------------------------------------------------");
			System.out.println("원하는 서비스의 번호를 입력하시오.");
			System.out.print("번호 입력 : ");
			select = scan.nextInt();
			scan.nextLine();
			System.out.println();
			System.out.println("---------------------------------------------------------------");

			// 콘서트---------------------------------------------------------------------------------
			if (select == 1) {
				ConcertInfo();
			}

			// 예약하기---------------------------------------------------------------------------------
			if (select == 2) {
				if (booking != null) {
					booking.booking();
				}
			}

			// 마이페이지---------------------------------------------------------------------------------
			if (select == 3) {
				if (mp != null) {
					mp.mypage();
				}
			}

			// 문의사항---------------------------------------------------------------------------------
			if (select == 4) {
				question();
			}

			// 로그인---------------------------------------------------------------------------------
			if (select == 5) {
				if (ps1 != null) {
					ps1.Login();
				}
			}
			
			// 회원가입------------------------------------------------------------
			if (select == 6) {
				if (ps1 != null) {
					ps2.Signup();
				}
			}

			// 관리자모드---------------------------------------------------------------------------------
			if (select == 7) {
				manager();
			}

			// 종료---------------------------------------------------------------------------------
			if (select == 0) {
				System.out.print("감사합니다. 안녕히가시오.");
				break;
			}
		}
	}

	public void ConcertInfo() {
		while (true) {
			// 메뉴 출력 & 사용자 선택
			System.out.println("1. 콘서트 정보 조회");
			System.out.println("0. 이전 메뉴로 돌아가기");
			System.out.print("번호 입력 : ");
			int choice1 = scan.nextInt();
			scan.nextLine();
			System.out.println();
			System.out.println("---------------------------------------------------------------");

			if (choice1 == 1) {
				// 콘서트 정보 출력
				System.out.println("<콘서트 INFORMATION>");
				System.out.println("번호\t콘서트명\t\t\t\t장르\t러닝타임\t날짜\t\t장소\t\t\t\t시간");
				for (ConInfoVO vo : clist) {
					System.out.println(String.format("%-1s\t%-24s\t%-5s\t%-5s\t%-10s\t%-24s\t%-10s", vo.getConcert_id(),
							vo.getTitle(), vo.getGenre(), vo.getRunning_time(), vo.getConcert_date().substring(0, 10),
							vo.getLocation(), vo.getTime()));
				}
				System.out.println("---------------------------------------------------------------");
			} else if (choice1 == 0) {
				// 이전 메뉴로 돌아가기
				break;
			} else {
				System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
			}

		}

	}

	public void question() {
		System.out.println("1.FAQ 2.Q&A");
		while (true) {
			System.out.print("번호 입력 : ");
			select = scan.nextInt();
			scan.nextLine();
			if (select == 1) {
				if (mp != null) {
					faq.faqStart();
					break;
				}
			} else if (select == 2) {
				if (qna != null) {
					qna.qnaStart();
					break;
				}
			} else {
				System.out.println("잘못된 입력입니다. 다시 입력하세요.");
			}
		}

	}

	public void CustomerInfo() {
		System.out.println("<고객 정보>");
		System.out.println("1. 모두 보기 2.아이디로 찾기");
		System.out.print("번호 입력 : ");
		int choice = scan.nextInt();
		System.out.println();
		System.out.println("---------------------------------------------------------------");

		if (choice == 1) {
			// 회원정보 전체 조회
			System.out.println("사용자ID\t이메일\t\t전화번호\t\t생년월일\t\t성별");
			for (UsersVO vo : ulist) {
				System.out.println("현재 회원 정보:");
				System.out.println(String.format("%-8s\t%-18s\t%-15s\t%-10s\t%-2s", vo.getUser_id(), vo.getEmail(),
						vo.getPhonenumber(), vo.getBirthday(), vo.getGender()));
			}

			System.out.println();
			System.out.println("---------------------------------------------------------------");
		}
		if (choice == 2) {
			// 개별 고객 조회 (포인트 포함)
			Scanner scanner = new Scanner(System.in);
			System.out.println("조회할 아이디를 입력하세요");
			System.out.print("아이디 : ");
			String useridscanner = scanner.nextLine();

			System.out.println();
			UsersDAO usersDAO2 = new UsersDAO();
			UsersVO user = usersDAO2.selectOne(useridscanner);
			PointDAO pointDAO = new PointDAO();
			PointVO point = pointDAO.selectOne(useridscanner);

			if (user != null) {
				System.out.println(useridscanner + "님 회원 정보:");
				System.out.println("아이디: " + user.getUser_id());
				System.out.println("이메일: " + user.getEmail());
				System.out.println("전화번호: " + user.getPhonenumber());
				System.out.println("생년월일: " + user.getBirthday());
				System.out.println("성별: " + user.getGender());
				if (point != null) {
					System.out.println("포인트: " + point.getPoint());
				} else {
					System.out.println("포인트: 0");
				}
			} else {
				System.out.println(useridscanner + "님의 회원 정보를 찾을 수 없습니다.");
			}
			System.out.println();
			System.out.println("---------------------------------------------------------------");
		}
	}

	public void CustomerPayInfo() {
			System.out.println("<고객 결제정보>");

			PayDao paymentDAO = new PayDao();
			List<Pay_VO> paymentList = paymentDAO.selectAll(); // PaymentDAO에서 결제 정보를 가져오는 메소드를 호출

			System.out.println("번호\t사용자ID\t\t결제방법\t\t금액\t\t결제일\t\t예약번호");
			for (Pay_VO vo : paymentList) {
				System.out.println(
						String.format("%-1s\t%-12s\t%-10s\t%-10s\t%-12s\t%-10s", vo.getPayment_id(), vo.getUser_id(),
								vo.getPayment_method(), vo.getTotal_price(), vo.getPayment_date(), vo.getBook_id()));
			}

			System.out.println();
			System.out.println("---------------------------------------------------------------");
	}

	public void CustomerBookInfo() {
		HashMap<Integer, String> bookedseat = new HashMap<>();
		for (Seat_infoVO vo1 : slist) {
			for (ReservationVO vo : rlist) {
				if (vo.getHall_id() == vo1.getHall_id() && vo.getSeat().equalsIgnoreCase(vo1.getSeat_no())) {
					bookedseat.put(vo1.getSeat_id(), vo1.getSeat_no());
				}
			}
		}

		System.out.println("<고객 예매정보>");

		System.out.println("예약ID\t사용자ID\t콘서트ID\t홀ID\t연락처\t\t예매인원\t좌석번호\t좌석가격\t수령방법\t예약날짜\t\t상태");
		for (ReservationVO vo : rlist) {
			System.out.println(String.format("%-1s\t%-7s\t%-1s\t%-1s\t%-7s\t%-1s\t%-5s\t%-1s\t%-1s" + "\t%-1s\t%-51s",
					vo.getBook_id(), vo.getUser_id(), vo.getConcert_id(), vo.getHall_id(), vo.getPhonenumber(),
					vo.getCount(), vo.getSeat(), vo.getPrice(), vo.getHowtoget(), vo.getCreateDate(), vo.getStatus()));
			System.out.println("-------------------------------------------------------------------------");
		}

		System.out.println();
		System.out.println("원하시는 기능의 번호를 입력하세요.");
		System.out.println("1.예매정보 수정 2.예매정보 삭제 3.관리자모드로 돌아가기");
		while (true) {
			System.out.print("번호 입력 : ");
			intscanner = scan.nextInt();
			scan.nextLine();
			System.out.println();
			if (intscanner == 1) {
				System.out.println("<고객 예매정보 수정>");
				System.out.println("수정할 고객의 예약ID 번호를 입력하세요.");
				while (true) {
					System.out.print("번호 입력 : ");
					sscanner = scan.nextLine();
					ReservationVO selectedbook = reservationDAO.selectOne(sscanner);
					if (selectedbook != null) {
						System.out.println("1.연락처 2.좌석번호 3.수령방법");
						System.out.println("수정을 원하는 번호 입력 : ");
						intscanner = scan.nextInt();
						scan.nextLine();

						switch (intscanner) {
						case 1:
							while (true) {
								System.out.print("새로운 연락처 입력(010-0000-0000): ");
								sscanner = scan.nextLine();
								if (sscanner.matches("\\d{3}-\\d{4}-\\d{4}")) {
									selectedbook.setPhonenumber(sscanner);
									break; // 올바른 형식이므로 반복문 종료
								} else {
									System.out.println("잘못된 형식입니다. 다시 입력하세요.");
								}
							}
							break;
						case 2:
							List<Integer> seatidabouthall = new ArrayList<>();
							ConInfoVO id = conInfoDAO.selectOne(String.valueOf(selectedbook.getConcert_id()));

							for (Seat_infoVO vo : slist) {
								if (vo.getHall_id() == id.getHall_id()) {
									System.out.println(vo.getSeat_id() + "." + vo.getSeat_no() + " ");
									seatidabouthall.add(vo.getSeat_id());
								}

							}

							System.out.println("원하는 좌석의 번호를 입력하시오.");
							while (true) {
								System.out.print("새로운 좌석번호 입력: ");
								seatscanner = scan.nextInt();
								scan.nextLine();

								Seat_infoVO selectseat = seatInfoDAO.selectOne(String.valueOf(seatscanner));
								selectedbook.setSeat(selectseat.getSeat_no());

								if (seatscanner < seatidabouthall.get(0)
										|| seatscanner > seatidabouthall.get(seatidabouthall.size() - 1)) {
									System.out.println("유효하지 않은 좌석 번호입니다. 다시 입력하세요.");
									continue;
								} else if (bookedseat.containsValue(selectseat.getSeat_no())) {
									System.out.println("이미 예약된 좌석 번호입니다. 다른 좌석 번호를 입력하세요.");
									continue;
								}
								for (Seat_infoVO vo : slist) {
									if (vo.getSeat_id() == seatscanner) {
									}
								}
								System.out.println(selectseat.getSeat_no() + " 좌석을 선택하셨습니다.");
								break;
							}
							break;
						case 3:
							System.out.print("새로운 수령방법 입력: ");
							sscanner = scan.nextLine();
							selectedbook.setHowtoget(sscanner);
						default:
							System.out.println("잘못된 선택입니다.");
						}

						int updateResult = reservationDAO.update(selectedbook);
						if (updateResult > 0) {
							System.out.println("고객 예매정보가 수정되었습니다.");
							System.out.println();
						} else {
							System.out.println("고객 예매정보 수정에 실패하였습니다.");
						}
						break;
					} else {
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
					}
				}
				break;
			}

			if (intscanner == 2) {
				System.out.println("<고객 예매정보 삭제>");
				while (true) {
					System.out.print("삭제할 고객의 예약ID 번호 입력: ");
					sscanner = scan.nextLine();
					ReservationVO selectedbook = reservationDAO.selectOne(sscanner);

					if (selectedbook != null) {
						int deleteResult = reservationDAO.delete(selectedbook.getBook_id());
						System.out.println("예약번호 " + selectedbook.getBook_id() + "번의 예매정보가 삭제되었습니다.");
						break;
					} else {
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
					}
					break;
				}

			}
			if (intscanner == 3) {
				System.out.println("---------------------------------------------------------------");
				System.out.println();
				System.out.println();
				break;
			} else {
				System.out.println("잘못된 번호입니다. 다시 번호를 입력 하세요. ");
			}
		}
	}

	public void editConcert() {
		System.out.println("1. 콘서트 정보 수정");
		System.out.println("2. 콘서트 정보 삭제");
		System.out.println("3. 이전 메뉴로 돌아가기");
		System.out.print("번호 입력 : ");
		int adminChoice = scan.nextInt();
		scan.nextLine(); // 개행 문자 처리

		if (adminChoice == 1) {
			System.out.println("<콘서트 INFORMATION>");
			System.out.println("번호\t콘서트명\t\t\t\t장르\t러닝타임\t날짜\t\t장소\t\t\t\t시간");
			for (ConInfoVO vo : clist) {
				System.out.println(String.format("%-1s\t%-24s\t%-5s\t%-5s\t%-10s\t%-24s\t%-10s", vo.getConcert_id(),
						vo.getTitle(), vo.getGenre(), vo.getRunning_time(), vo.getConcert_date().substring(0, 10),
						vo.getLocation(), vo.getTime()));
			}
			System.out.println("---------------------------------------------------------------");
			// 콘서트 정보 수정
			System.out.print("수정할 콘서트의 번호 입력: ");
			int concertNumber = scan.nextInt();
			scan.nextLine(); // 개행 문자 처리

			ConInfoVO selectedConcert = conInfoDAO.selectOne(Integer.toString(concertNumber));
			if (selectedConcert != null) {
				System.out.println("수정할 필드를 선택하세요.");
				System.out.println("1. 제목 2. 장르 3. 러닝타임 4. 날짜 5. 장소 6. 시간");
				System.out.print("번호 입력: ");
				int fieldChoice = scan.nextInt();
				scan.nextLine(); // 개행 문자 처리

				switch (fieldChoice) {
				case 1:
					System.out.print("새로운 제목 입력: ");
					String newTitle = scan.nextLine();
					selectedConcert.setTitle(newTitle);
					break;
				case 2:
					System.out.print("새로운 장르 입력: ");
					String newGenre = scan.nextLine();
					selectedConcert.setGenre(newGenre);
					break;
				case 3:
					System.out.print("새로운 러닝타임 입력: ");
					int newRunningTime = scan.nextInt();
					selectedConcert.setRunning_time(newRunningTime);
					break;
				case 4:
					System.out.print("새로운 날짜 입력 (YYYY-MM-DD): ");
					String newConcertDate = scan.nextLine();
					selectedConcert.setConcert_date(newConcertDate);
					break;
				case 5:
					System.out.print("새로운 장소 입력: ");
					String newLocation = scan.nextLine();
					selectedConcert.setLocation(newLocation);
					break;
				case 6:
					System.out.print("새로운 시간 입력: ");
					String newTime = scan.nextLine();
					selectedConcert.setTime(newTime);
					break;
				default:
					System.out.println("잘못된 선택입니다.");
				}

				// 수정된 콘서트 정보를 데이터베이스에 업데이트
				int updateResult = conInfoDAO.update(selectedConcert);
				if (updateResult > 0) {
					System.out.println("콘서트 정보가 수정되었습니다.");
				} else {
					System.out.println("콘서트 정보 수정에 실패하였습니다.");
				}
			} else {
				System.out.println("해당 번호의 콘서트 정보가 없습니다.");
			}
		}

		if (adminChoice == 2) {
			System.out.print("삭제할 콘서트의 번호 입력: ");
			int concertNumber = scan.nextInt();
			scan.nextLine(); // 개행 문자 처리

			ConInfoVO selectedConcert = conInfoDAO.selectOne(Integer.toString(concertNumber));
			if (selectedConcert != null) {
				// 콘서트 정보를 데이터베이스에서 삭제
				int deleteResult = conInfoDAO.delete(selectedConcert);
				if (deleteResult > 0) {
					System.out.println("콘서트 정보가 삭제되었습니다.");
				} else {
					System.out.println("콘서트 정보 삭제에 실패하였습니다.");
				}
			} else {
				System.out.println("해당 번호의 콘서트 정보가 없습니다.");
			}

			// "콘서트 정보 삭제" 작업이 완료되었으므로 select 값을 변경
			select = 0; // 0은 종료를 나타내는 값으로 설정
		}

		if (select == 3) {

		}

		

	}

	public void manager() {
		System.out.println("관리자 비밀번호를 입력하세요.");

		while (true) {
			// 1. 관리자 비밀번호 입력
			System.out.print("비밀번호 : ");
			sscanner = scan.nextLine(); // 비밀번호 맞으면 다음으로 넘어가게 하기
			System.out.println();
			System.out.println("---------------------------------------------------------------");

			if (MANAGERPW.equalsIgnoreCase(sscanner)) {
				System.out.println("관리자 모드로 변경되었습니다.");
				System.out.println("---------------------------------------------------------------");
				while (true) {
					// 2. 원하는 메뉴 선택하기

					System.out.println("<관리자 모드>");
					System.out.println("1.고객정보 2.고객 결제정보 3.고객 예매정보 4.콘서트 정보 수정 5.관리자모드 종료");
					System.out.print("번호 입력 : ");
					select = scan.nextInt();
					scan.nextLine();
					System.out.println("---------------------------------------------------------------");

					// 2-1. 고객정보 조회 (users select all)
					if (select == 1) {
						CustomerInfo();
					}
					// 2-2. 고객 결제정보
					if (select == 2) {
						CustomerPayInfo();
					}
					// 2-3. 고객 예매 정보
					if (select == 3) {
						CustomerBookInfo();
					}
					// 2-4. 콘서트 정보 수정 (concert_info insert, update, delete)
					if (select == 4) {
						editConcert();
					}
					if (select == 5) {
						System.out.println("관리자 모드가 종료되었습니다.");
						System.out.println("---------------------------------------------------------------");
						System.out.println();
						System.out.println();
						break;
					}

				}
				if (select == 5) {
					break;
				}

			} else {
				// 올바르지 않은 비밀번호를 입력한 경우
				System.out.println("비밀번호가 올바르지 않습니다. 다시 입력하세요.");
			}
		}
	}
}
