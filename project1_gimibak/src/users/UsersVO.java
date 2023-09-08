package users;

public class UsersVO {

	private String user_id;
	private String email;
	private String password;
	private String phonenumber;
	private String birthday;
	private String gender;
	
	public UsersVO(String user_id, String email, String password, String phonenumber, String birthday, String gender) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.phonenumber = phonenumber;
		this.birthday = birthday;
		this.gender = gender;
	}


	public UsersVO(String user_id, String password) {
		super();
		this.user_id = user_id;
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UsersVO [user_id=" + user_id + ", email=" + email + ", password=" + password + ", phonenumber="
				+ phonenumber + ", birthday=" + birthday + ", gender=" + gender + "]";
	}
	
	

}
