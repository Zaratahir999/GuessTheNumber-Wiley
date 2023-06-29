package com.teamtwo.model.persistence;

import java.util.List;

import com.teamtwo.entity.Game;

public interface GameDao {
	
	 List<Game> getAllGames();
	 Game getGamebyId (int gameId);
	 Game addGame (Game game);
//	 int  updateGame (String status, int gameId);

	boolean updateGame(Game game);
	 
	 

}
