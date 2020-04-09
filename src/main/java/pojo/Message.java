package pojo;

public class Message {

	private int message_id;
	private int user_id;
	private int schedule_id;
	private String user_phone;
	private String messgae_context;
	private String message_date;

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getMessgae_context() {
		return messgae_context;
	}

	public void setMessgae_context(String messgae_context) {
		this.messgae_context = messgae_context;
	}

	public String getMessage_date() {
		return message_date;
	}

	public void setMessage_date(String message_date) {
		this.message_date = message_date;
	}

	@Override
	public String toString() {
		return "Message [message_id=" + message_id + ", user_id=" + user_id + ", schedule_id=" + schedule_id
				+ ", user_phone=" + user_phone + ", messgae_context=" + messgae_context + ", message_date="
				+ message_date + "]";
	}

}
