package com.teamtwo.entity;

import java.time.LocalDateTime;

import javax.print.attribute.standard.DateTimeAtCompleted;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Round {
	
	private int roundId;
	private int gameId;
	private String guess;
	private LocalDateTime guessTime;
	private String result;
	
	
	public Round(int roundId, int gameId, String guess, LocalDateTime guessTime, String result) {
		super();
		this.roundId = roundId;
		this.gameId = gameId;
		this.guess = guess;
		this.guessTime = guessTime;
		this.result= result;
	}
	public int getRoundId() {
		return roundId;
	}
	public void setRoundId(int roundId) {
		this.roundId = roundId;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getGuess() {
		return guess;
	}
	public void setGuess(String guess) {
		this.guess = guess;
	}
	public LocalDateTime getGuessTime() {
		return guessTime;
	}
	public void setGuessTime(LocalDateTime guessTime) {
		this.guessTime = guessTime;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.guess = result;
	}
	@Override
	public String toString() {
		return "Round [roundId=" + roundId + ", gameId=" + gameId + ", guess=" + guess + ", guessTime=" + guessTime
				+ ", result=" + result + "]";
	}
		

}
