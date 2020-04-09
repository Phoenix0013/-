package pojo;

public class Schedule {
	private int schedule_id;
	private String home_team;
	private String awayhome_team;
	private String match_time;
	private String league_membership;

	public int getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getHome_team() {
		return home_team;
	}

	public void setHome_team(String home_team) {
		this.home_team = home_team;
	}

	public String getAwayhome_team() {
		return awayhome_team;
	}

	public void setAwayhome_team(String awayhome_team) {
		this.awayhome_team = awayhome_team;
	}

	public String getMatch_time() {
		return match_time;
	}

	public void setMatch_time(String match_time) {
		this.match_time = match_time;
	}

	public String getLeague_membership() {
		return league_membership;
	}

	public void setLeague_membership(String league_membership) {
		this.league_membership = league_membership;
	}

	@Override
	public String toString() {
		return "Schedule [schedule_id=" + schedule_id + ", home_team=" + home_team + ", awayhome_team=" + awayhome_team
				+ ", match_time=" + match_time + ", league_membership=" + league_membership + "]";
	}

}
