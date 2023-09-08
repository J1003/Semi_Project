package qna;

public class QnaVO {
	//필드-------------------------------------------------------------
	private int Qna_id;
	private String User_id;
	private String Email;
	private String Question;
	private String Status;
	
	//생성자-------------------------------------------------------------
	public QnaVO() {}

	public QnaVO(int Qna_id, String User_id, String Email, String Question, String Status){
		super();
		this.Qna_id = Qna_id;
		this.User_id = User_id;
		this.Email = Email;
		this.Question = Question;
		this.Status = Status;
	}
	
	
	//메소드-------------------------------------------------------------
	


	@Override
	public String toString() {
		return "FaqVO [Qna_id= " + Qna_id + "User_id= " + User_id + "Email= " + Email + 
				"Question= " + Question + "Status=" + Status + "]";
				
	}

	public int getQna_id() {
		return Qna_id;
	}

	public void setQna_id(int qna_id) {
		Qna_id = qna_id;
	}

	public String getUser_id() {
		return User_id;
	}

	public void setUser_id(String user_id) {
		User_id = user_id;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
}
