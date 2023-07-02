package com.teamtwo.entity;

import java.sql.Timestamp;

public class Round {
	
	private int roundId;
	private int gameId;
	private String guess;
	private String result;
	private Timestamp guessTime;

	public Round() {
	}

	public Round(int roundId, int gameId, String guess, String result, Timestamp guessTime) {
		this.roundId = roundId;
		this.gameId = gameId;
		this.guess = guess;
		this.result = result;
		this.guessTime = guessTime;
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Timestamp getGuessTime() {
		return guessTime;
	}

	public void setGuessTime(Timestamp guessTime) {
		this.guessTime = guessTime;
	}

	@Override
	public String toString() {
		return "Round{" +
				"roundId=" + roundId +
				", gameId=" + gameId +
				", guess='" + guess + '\'' +
				", result='" + result + '\'' +
				", guessTime=" + guessTime +
				'}';
	}
}