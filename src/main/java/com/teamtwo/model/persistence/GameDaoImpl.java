package com.teamtwo.model.persistence;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.teamtwo.entity.Game;
@Repository
public class GameDaoImpl implements GameDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Game> getAllGames() {
		return jdbcTemplate.query("SELECT * FROM GAME", new GameMapper());
	}

	@Override
	public Game getGamebyId(int gameId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM GAME WHERE GAMEID = ?", new GameMapper(), gameId);
		}
		catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Game addGame(Game game) {
		try {
			jdbcTemplate.update("INSERT INTO GAME(ANSWER) VALUES (?)",
					game.getAnswer());
			game.setGameId(getLastGameId());
			return game;
		}catch (DataAccessException ex) {
			return  null;
		}
	}

	@Override
	public boolean updateGame(Game game) {
		return jdbcTemplate.update("UPDATE GAME SET ANSWER = ? , FINISHED= ? WHERE GAMEID = ?",
				game.getAnswer(),
				game.isFinished(),
				game.getGameId())>0;
	}

	private int getLastGameId() {
		return jdbcTemplate.queryForObject("SELECT GAMEID FROM GAME ORDER BY GAMEID DESC LIMIT 1", Integer.class);
	}
}