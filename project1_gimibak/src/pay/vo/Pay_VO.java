package pay.vo;

public class Pay_VO {

	private int payment_id;
	private String user_id;
	private String payment_method;
	private int total_price;
	private String payment_date;
	private String Book_id;
	
	
	public Pay_VO(int payment_id, String user_id, String payment_method, int total_price, String payment_date,
				  String Book_id) {
		super();
		this.payment_id = payment_id;
		this.user_id = user_id;
		this.payment_method = payment_method;
		this.total_price = total_price;
		this.payment_date = payment_date;
		this.Book_id = Book_id;
	}


	public int getPayment_id() {
		return payment_id;
	}


	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getPayment_method() {
		return payment_method;
	}


	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}


	public int getTotal_price() {
		return total_price;
	}


	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}


	public String getPayment_date() {
		return payment_date;
	}


	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}


	public String getBook_id() {
		return Book_id;
	}


	public void setBook_id(String Book_id) {
		this.Book_id = Book_id;
	}


	@Override
	public String toString() {
		return "Pay_VO [payment_id=" + payment_id + ", user_id=" + user_id + ", payment_method=" + payment_method
				+ ", total_price=" + total_price + ", payment_date=" + payment_date + ", Book_id="
				+ Book_id + "]";
	}

	
	
}
