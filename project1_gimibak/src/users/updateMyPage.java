package users;

import java.util.Scanner;

public class updateMyPage {

    public void UpdateUserInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("회원 정보 수정을 진행하세요");
        
        System.out.print("수정할 사용자 아이디를 입력하세요 : ");
        String user_id = sc.nextLine();

        UsersDAO usersDAO = new UsersDAO();
        UsersVO user = usersDAO.selectOne(user_id);

        if (user != null) {
            System.out.print("비밀번호를 입력하세요: ");
            String password = sc.nextLine();

            if (user.getPassword().equals(password)) {
                System.out.println("비밀번호 확인 성공");
                System.out.println("현재 회원 정보:");
                System.out.println("아이디: " + user.getUser_id());
                System.out.println("이메일: " + user.getEmail());
                System.out.println("전화번호: " + user.getPhonenumber());
                System.out.println("생년월일: " + user.getBirthday());
                System.out.println("성별: " + user.getGender());
                
                System.out.println("어떤 항목을 수정하시겠습니까?");
                System.out.println("1. 이메일");
                System.out.println("2. 전화번호");
                System.out.println("3. 생년월일");
                System.out.println("4. 성별");
                System.out.println("5. 취소");
                
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("새로운 이메일을 입력하세요: ");
                        String newEmail = sc.nextLine();
                        user.setEmail(newEmail);
                        break;
                    case 2:
                        System.out.print("새로운 전화번호를 입력하세요(000-0000-0000): ");
                        String newPhoneNumber = sc.nextLine();
                        user.setPhonenumber(newPhoneNumber);
                        break;
                    case 3:
                        System.out.print("새로운 생년월일(숫자 8자리 ex) 19450815)을 입력하세요: ");
                        String newBirthday = sc.nextLine();
                        user.setBirthday(newBirthday);
                        break;
                    case 4:
                        System.out.print("새로운 성별(M/F)을 입력하세요: ");
                        String newGender = sc.nextLine();
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
                } else {
                    System.out.println("회원 정보 수정에 실패하였습니다.");
                }
            } else {
                System.out.println("비밀번호가 일치하지 않습니다. 회원 정보 수정을 취소합니다.");
            }
        } else {
            System.out.println("해당 아이디의 사용자가 존재하지 않습니다.");
        }

        sc.close();
    }
    
    
    public void deleteUserInfo() {
    	Scanner sc = new Scanner(System.in);

    	System.out.print("삭제할 사용자 아이디를 입력하세요: ");
    	String user_id = sc.nextLine();

    	UsersDAO usersDAO = new UsersDAO();
    	UsersVO user = usersDAO.selectOne(user_id);

    	if (user != null) {
    	    System.out.print("비밀번호를 입력하세요: ");
    	    String password = sc.nextLine();
    	    
    	    if (user.getPassword().equals(password)) {
    	        int result = usersDAO.delete(user_id);
    	        
    	        if (result > 0) {
    	            System.out.println("회원 정보가 성공적으로 삭제되었습니다.");
    	        } else {
    	            System.out.println("회원 정보 삭제에 실패하였습니다.");
    	        }
    	    } else {
    	        System.out.println("비밀번호가 일치하지 않습니다. 회원 정보 삭제를 취소합니다.");
    	    }
    	} else {
    	    System.out.println("해당 아이디의 사용자가 존재하지 않습니다.");
    	}

    	sc.close();
    }
    
    
}
