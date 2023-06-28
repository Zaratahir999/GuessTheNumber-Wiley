package com.teamtwo.model.persistence;

import java.util.List;

import com.teamtwo.entity.Game;

public interface GameDao {
	
	 List<Game> getAllGames();
	 Game getGamebyId (int gameId);
	 Game addGame (Game game);
	 boolean updateGame (Game game);
	 
	 
	 

}
