package pojo;

public class Replay {
	private int reply_id;
	private int user_id;
	private int message_id;
	private String replay_context;
	private String replay_date;
	private String user_phone;

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getReplay_context() {
		return replay_context;
	}

	public void setReplay_context(String replay_context) {
		this.replay_context = replay_context;
	}

	public String getReplay_date() {
		return replay_date;
	}

	public void setReplay_date(String replay_date) {
		this.replay_date = replay_date;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	@Override
	public String toString() {
		return "Replay [reply_id=" + reply_id + ", user_id=" + user_id + ", message_id=" + message_id
				+ ", replay_context=" + replay_context + ", replay_date=" + replay_date + ", user_phone=" + user_phone
				+ "]";
	}

}
