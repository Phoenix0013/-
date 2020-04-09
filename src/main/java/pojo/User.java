package pojo;


public class User {
	private int user_id;
	private String user_phone;
	private String user_password;
	private String user_state;
	private String user_points;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_state() {
		return user_state;
	}

	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}

	public String getUser_points() {
		return user_points;
	}

	public void setUser_points(String user_points) {
		this.user_points = user_points;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_phone=" + user_phone + ", user_password=" + user_password
				+ ", user_state=" + user_state + ", user_points=" + user_points + "]";
	}

}
