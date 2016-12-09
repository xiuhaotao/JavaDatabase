package exercise1;

import java.util.Calendar;

public class ReportDisplayModel {
	private String playerName;
	private String gameName;
	private Calendar playingDate;
	private int score;
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public Calendar getPlayingDate() {
		return playingDate;
	}
	public void setPlayingDate(Calendar playingDate) {
		this.playingDate = playingDate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
