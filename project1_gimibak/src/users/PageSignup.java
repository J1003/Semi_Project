package users;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;



public class PageSignup {
    public void Signup() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);

        System.out.println("회원가입을 진행하세요");

        while (true) {
            System.out.print("사용할 아이디를 입력하세요 : ");
            String user_id = sc.nextLine();

            // UsersDAO를 이용하여 데이터베이스에서 해당 아이디가 이미 존재하는지 확인
            UsersDAO usersDAO = new UsersDAO();
            UsersVO existingUser = usersDAO.selectOne(user_id);

            if (existingUser == null) {
                System.out.print("사용할 이메일을 입력하세요 : ");
                String email = sc.nextLine();

                System.out.print("비밀번호를 입력하세요 : ");
                String password = sc.nextLine();

                System.out.print("비밀번호를 다시 입력하세요 : ");
                String passwordConfirm = sc.nextLine();

                if (!password.equals(passwordConfirm)) {
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 입력하세요.");
                    continue; // 비밀번호부터 다시 입력
                }

                while (true) {
                    System.out.print("전화번호를 입력하세요(000-0000-0000) : ");
                    String phoneNumber = sc.nextLine();

                    // 전화번호의 유효성을 정규표현식으로 검사
                    String phoneNumberPattern = "\\d{3}-\\d{4}-\\d{4}";
                    if (!phoneNumber.matches(phoneNumberPattern)) {
                        System.out.println("유효한 전화번호 형식이 아닙니다. 다시 입력하세요.");
                    } else {
                        while (true) {
                            System.out.print("생년월일(숫자 8자리 ex) 19450815) : ");
                            String birthday = sc.nextLine();

                            if (birthday.length() != 8) {
                                System.out.println("생년월일은 8자리로 입력하세요.");
                                continue;
                            }

                            try {
                                dateFormat.parse(birthday);

                                while (true) {
                                    System.out.print("성별은 무엇입니까? (M/F) : ");
                                    String gender = sc.nextLine();

                                    if (!gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
                                        System.out.println("성별은 'M' 또는 'F' 중에서 입력하세요.");
                                    } else {
                                        // 입력 받은 정보로 UsersVO 객체 생성
                                        UsersVO newUser = new UsersVO(user_id, email, password, phoneNumber, birthday, gender);

                                        // UsersDAO를 이용하여 데이터베이스에 추가
                                        int result = usersDAO.insert(newUser);

                                        if (result > 0) {
                                            System.out.println("회원가입이 성공적으로 완료되었습니다.");
                                        } else {
                                            System.out.println("회원가입에 실패하였습니다.");
                                        }

                                        break; // 회원가입 완료 후 반복문 탈출
                                    }
                                }
                                break; // 성별 검증 루프 탈출
                            } catch (ParseException e) {
                                System.out.println("유효하지 않은 날짜입니다. 다시 입력하세요.");
                            }
                        }
                        break; // 생년월일 검증 루프 탈출
                    }
                }
                break; // 회원가입 완료 후 반복문 탈출
            } else {
                System.out.println("이미 사용 중인 아이디입니다. 다른 아이디를 입력하세요.");
            }
        }

        sc.close();
    }
    
	//로그인 메서드	
	public void Login () {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
            System.out.print("아이디를 입력하세요 : ");
            String user_id = sc.nextLine();

            // UsersDAO를 이용하여 데이터베이스에서 사용자 정보 조회
            UsersDAO usersDAO = new UsersDAO();
            UsersVO user = usersDAO.selectOne(user_id);

            if (user != null) {
            	while (true) {
                    System.out.print("비밀번호를 입력하세요: ");
                    String password = sc.nextLine();

                    if (user.getPassword().equals(password)) {
                        System.out.println("로그인에 성공했습니다.");
                        break;
                    } else {
                        System.out.println("비밀번호가 일치하지 않습니다. 다시 입력하세요");
                    }
                }
                break; 
            } else {
                System.out.println("일치하는 아이디가 없습니다. 다시 입력하세요");
            }
        }
			sc.close();
	}
}
