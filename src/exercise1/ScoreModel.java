package exercise1;

import java.util.Calendar;

public class ScoreModel {
	private int id;
	private int gameId;
	private int playerId;
	private Calendar playDate;
	private int score;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public Calendar getPlayDate() {
		return playDate;
	}
	public void setPlayDate(Calendar playDate) {
		this.playDate = playDate;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
