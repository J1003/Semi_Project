package faq;

public class FaqVO {
	//필드-------------------------------------------------------------
	private int faq_id;
	private String question;
	private String answer;
	
	//생성자-------------------------------------------------------------
	public FaqVO() {}

	public FaqVO(int faq_id, String question, String answer){
		super();
		this.faq_id = faq_id;
		this.question = question; 
		this.answer = answer;
	}
	
	
	//메소드-------------------------------------------------------------
	

	public int getFaq_id() {
		return faq_id;
	}

	public void setFaq_id(int faq_id) {
		this.faq_id = faq_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "FaqVO [faq_id=" + faq_id + ", question=" + question + 
				", answer=" + answer + "]";
	}
		
	
}
