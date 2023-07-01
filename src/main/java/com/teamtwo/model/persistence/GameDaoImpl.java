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
		return jdbcTemplate.query("SELECT * FROM game", new GameMapper());
	}
	@Override
	public Game getGamebyId(int gameId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM game WHERE GAMEID = ?", new GameMapper(), gameId);
		}
		catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Game addGame(Game game) {
		try {
			jdbcTemplate.update("INSERT INTO game(answer) VALUES (?)",game.getAnswer());
			game.setGameId(getLastGameId());
			return game;
		}catch (DataAccessException ex) {
			return  null;
		}
	}

	private int getLastGameId() {
		return jdbcTemplate.queryForObject("SELECT gameId FROM game ORDER BY gameId DESC LIMIT 1", Integer.class);
	}


//	@Override
//	public int updateGame(String status, int gameId) {
//		try {
//			return jdbcTemplate.update("UPDATE GUESSNUMBER SET STATUS = ? WHERE GAMEID= ?", status, gameId);
//
//		}catch (Exception ex) {
//			return 0;
//		}
//	}
	@Override
	public boolean updateGame(Game game) {
		final String UPDATE_GAME = "UPDATE game SET ANSWER = ? , STATUS= ? WHERE GAMEID = ?";
		return jdbcTemplate.update(UPDATE_GAME, game.getAnswer(),game.isFinished(), game.getGameId())>0;

	}


}
