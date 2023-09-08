package seat_info;

public class Seat_infoVO {
	//필드-------------------------------------------------------------
	private int seat_id;
	private int hall_id;
	private String seat_no;
	private String seat_price;
	private String hallname;
	
	//생성자-------------------------------------------------------------
	public Seat_infoVO() {}

	public Seat_infoVO(int seat_id, int hall_id, String seat_no, String seat_price, String hallname) {
		super();
		this.seat_id = seat_id; 
		this.hall_id = hall_id; 
		this.seat_no = seat_no; 
		this.seat_price = seat_price; 
		this.hallname = hallname; 
	}
	
	//메소드-------------------------------------------------------------

	public String getHallname() {
		return hallname;
	}

	public void setHallname(String hallname) {
		this.hallname = hallname;
	}

	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}

	public int getHall_id() {
		return hall_id;
	}

	public void setHall_id(int hall_id) {
		this.hall_id = hall_id;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}

	public String getSeat_price() {
		return seat_price;
	}

	public void setSeat_price(String seat_price) {
		this.seat_price = seat_price;
	}
		

	@Override
	public String toString() {
		return "Seat_infoVO [seat_id=" + seat_id + "hall_id=" + hall_id + ", seat_no=" + seat_no + 
				", seat_price=" + seat_price + ", hallname" + hallname + "]";
	}
}
