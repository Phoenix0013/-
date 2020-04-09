package pojo;

public class Player {
	private int player_id;
	private String player_Ename;
	private String player_Cname;
	private String player_goal;
	private String player_assists;
	private String player_interceptions;
	private String player_number;
	private String player_effectiveTeam;

	public Player(String player_Ename, String player_Cname, String player_goal, String player_assists,
			String player_interceptions, String player_number, String player_effectiveTeam) {
		super();
		this.player_Ename = player_Ename;
		this.player_Cname = player_Cname;
		this.player_goal = player_goal;
		this.player_assists = player_assists;
		this.player_interceptions = player_interceptions;
		this.player_number = player_number;
		this.player_effectiveTeam = player_effectiveTeam;
	}

	public int getPlayer_id() {
		return player_id;
	}

	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}

	public String getPlayer_Ename() {
		return player_Ename;
	}

	public void setPlayer_Ename(String player_Ename) {
		this.player_Ename = player_Ename;
	}

	public String getPlayer_Cname() {
		return player_Cname;
	}

	public void setPlayer_Cname(String player_Cname) {
		this.player_Cname = player_Cname;
	}

	public String getPlayer_goal() {
		return player_goal;
	}

	public void setPlayer_goal(String player_goal) {
		this.player_goal = player_goal;
	}

	public String getPlayer_assists() {
		return player_assists;
	}

	public void setPlayer_assists(String player_assists) {
		this.player_assists = player_assists;
	}

	public String getPlayer_interceptions() {
		return player_interceptions;
	}

	public void setPlayer_interceptions(String player_interceptions) {
		this.player_interceptions = player_interceptions;
	}

	public String getPlayer_number() {
		return player_number;
	}

	public void setPlayer_number(String player_number) {
		this.player_number = player_number;
	}

	public String getPlayer_effectiveTeam() {
		return player_effectiveTeam;
	}

	public void setPlayer_effectiveTeam(String player_effectiveTeam) {
		this.player_effectiveTeam = player_effectiveTeam;
	}

	@Override
	public String toString() {
		return "Player [player_id=" + player_id + ", player_Ename=" + player_Ename + ", player_Cname=" + player_Cname
				+ ", player_goal=" + player_goal + ", player_assists=" + player_assists + ", player_interceptions="
				+ player_interceptions + ", player_number=" + player_number + ", player_effectiveTeam="
				+ player_effectiveTeam + "]";
	}

}
