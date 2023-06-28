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
	

}
