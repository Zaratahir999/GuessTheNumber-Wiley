package com.teamtwo.model.service;

import java.util.List;

import com.teamtwo.entity.Game;
import com.teamtwo.entity.Round;

public interface GuessNumberService {
	
	List<Game> getAllGames();
	Game getGameById (int gameId);
	Game addGame (Game game);
	boolean updateGame (Game game);
	List<Round> getAllRoundsbyGameId (int gameId);
	Round getRound (int roundId);
	Round addRound (Round round);
	Round guess (int gameId, String guess);
	Game startGame();
	String getGameResults (String guess, String answer);
	
	
	

}
