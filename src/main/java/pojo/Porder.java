package pojo;

public class Porder {
	private int order_id;
	private String user_phone;
	private String order_money;
	private String win_team;
	private String order_time;
	private String order_state;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getOrder_money() {
		return order_money;
	}

	public void setOrder_money(String order_money) {
		this.order_money = order_money;
	}

	public String getWin_team() {
		return win_team;
	}

	public void setWin_team(String win_team) {
		this.win_team = win_team;
	}

	public String getOrder_time() {
		return order_time;
	}

	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}

	@Override
	public String toString() {
		return "Porder [order_id=" + order_id + ", user_phone=" + user_phone + ", order_money=" + order_money
				+ ", win_team=" + win_team + ", order_time=" + order_time + ", order_state=" + order_state + "]";
	}

}
