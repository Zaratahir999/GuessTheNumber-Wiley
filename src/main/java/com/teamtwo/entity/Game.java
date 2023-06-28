package com.teamtwo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
	
	private int gameId;
	private String answer;
	private boolean status;
	
	
	public Game(int gameId, String answer, boolean status) {
		super();
		this.gameId = gameId;
		this.answer = answer;
		this.status = status;
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
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", answer=" + answer + ", status=" + status + "]";
	}

	

}
