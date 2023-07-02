package com.teamtwo.entity;

public class Game {
	
	private int gameId;
	private String answer;
	private boolean finished;

	public Game() {
	}

	public Game(int gameId, String answer, boolean finished) {
		this.gameId = gameId;
		this.answer = answer;
		this.finished = finished;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	@Override
	public String toString() {
		return "Game{" +
				"gameId=" + gameId +
				", answer='" + answer + '\'' +
				", finished=" + finished +
				'}';
	}
}