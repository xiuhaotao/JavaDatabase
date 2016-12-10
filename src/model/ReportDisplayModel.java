package model;

import java.util.Calendar;

public class ReportDisplayModel {
	private int id;
	private int playerId;
	private int gameId;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}
