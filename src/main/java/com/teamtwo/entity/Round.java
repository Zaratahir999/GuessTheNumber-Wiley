package com.teamtwo.entity;

import java.time.LocalDateTime;

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
	private String result;
	private LocalDateTime guessTime;
	

}
