package hall_info;

public class Hall_infoVO {
	//필드-------------------------------------------------------------
	private int hall_id;
	private String city;
	private String name;
	private int no_seats;
	
	//생성자-------------------------------------------------------------
	public Hall_infoVO() {}

	public Hall_infoVO(int hall_id, String city, String name, int no_seats) {
		super();
		this.hall_id = hall_id; 
		this.city = city; 
		this.name = name; 
		this.no_seats = no_seats;
	}
	
	//메소드-------------------------------------------------------------
	public int getHall_id() {
		return hall_id;
	}

	public void setHall_id(int hall_id) {
		this.hall_id = hall_id;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo_seats() {
		return no_seats;
	}

	public void setNo_seats(int no_seats) {
		this.no_seats = no_seats;
	}

	@Override
	public String toString() {
		return "Hall_infoVO [hall_id=" + hall_id + ", city=" + city + 
				", name=" + name + ", hall_id=" + hall_id + 
				", no_seats=" + no_seats + "]";
	}
		
	
}
