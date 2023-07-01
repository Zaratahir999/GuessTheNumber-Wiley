package com.teamtwo.model.service;

import java.util.List;

import com.teamtwo.entity.Game;
import com.teamtwo.entity.Round;

public interface GuessNumberService {
	
	List<Game> getAllGames();
	Game getGameById (int gameId);
	Game addGame (Game game);
//	boolean updateGame (String status, int gameId);
	List<Round> getAllRoundsbyGameId (int gameId);
	Round getRound (int roundId);
	Round addRound (Round round);
	Round guess (Round round);
	String getGameResults (String guess, String answer);
	String beginNewGame();
	String generateAnswer();
	

}
