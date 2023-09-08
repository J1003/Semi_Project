package reservation;

public class ReservationVO {
	//필드-------------------------------------------------------------
	private String book_id;
	private String user_id;
	private int concert_id;
	private int hall_id;
	private int count;
	private String seats;
	private String price;
	private String paymentmethod;
	private String createDate;
	private String status;
	private int seat_id;
	private String seat;
	private String phonenumber;
	private String howtoget;


	
	//생성자-------------------------------------------------------------
	public ReservationVO() {}

	public ReservationVO(String book_id, String user_id, int concert_id, int hall_id, String phonenumber, int count,
			String seat, String price, String paymentmethod, String howtoget, String createDate, String status) {
		super();
		this.book_id = book_id; //자동지정
		this.user_id = user_id; //USERS 테이블에서 가져오기
		this.concert_id = concert_id; //CONCERT_INFO 테이블에서 가져오기
		this.hall_id = hall_id; //HALL_INFO 테이블에서 가져오기
		this.phonenumber = phonenumber; //HALL_INFO 테이블에서 가져오기
		this.count = count; //예매하기 정보
		this.seat = seat; //예매하기 정보
		this.price = price; //예매하기 정보
		this.paymentmethod = paymentmethod; //예매하기 정보
		this.howtoget = howtoget; //예매하기 정보
		this.createDate = createDate; //예매하기 정보
		this.status = status; //기본은 정상
	}
	
	
	//메소드-------------------------------------------------------------
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getHowtoget() {
		return howtoget;
	}

	public void setHowtoget(String howtoget) {
		this.howtoget = howtoget;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getConcert_id() {
		return concert_id;
	}

	public void setConcert_id(int concert_id) {
		this.concert_id = concert_id;
	}

	public int getHall_id() {
		return hall_id;
	}

	public void setHall_id(int hall_id) {
		this.hall_id = hall_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeats(String seat) {
		this.seat = seat;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReservationVO [book_id=" + book_id + ", user_id=" + user_id + 
				", concert_id=" + concert_id + ", hall_id=" + hall_id + ", phonenumber" + phonenumber +
				", count=" + count + ", seat=" + seat + ", price=" + price + 
				", paymentmethod=" + paymentmethod + ", howtoget=" + howtoget + ", createDate=" + createDate + ", status=" + status + "]";
	}
		
	
}
