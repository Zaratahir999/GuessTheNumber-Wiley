package com.teamtwo.model.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teamtwo.entity.Game;

@Repository
public class GameDaoImpl implements GameDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Game> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game getGamebyId(int gameId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game addGame(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateGame(Game game) {
		// TODO Auto-generated method stub
		return false;
	}

}
