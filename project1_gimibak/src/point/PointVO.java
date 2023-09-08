package point;

public class PointVO {
	
	private int poi_id;
	private String  user_id;
	private int  point;
	private String  issue_date;
	private String  expiry_date;
	private String  memo;
	
	public PointVO(int poi_id, String user_id, int point, String issue_date, String expiry_date, String memo) {
		super();
		this.poi_id = poi_id;
		this.user_id = user_id;
		this.point = point;
		this.issue_date = issue_date;
		this.expiry_date = expiry_date;
		this.memo = memo;
	}

	public PointVO(String user_id, int point) {
		super();
		this.user_id = user_id;
		this.point = point;
	}

	public int getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(int poi_id) {
		this.poi_id = poi_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "PointVO [poi_id=" + poi_id + ", user_id=" + user_id + ", point=" + point + ", issue_date=" + issue_date
				+ ", expiry_date=" + expiry_date + ", memo=" + memo + "]";
	}
	
	
}
