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
		return jdbcTemplate.query("SELECT * FROM GUESSNUMBER", new GameMapper());
	}
	@Override
	public Game getGamebyId(int gameId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM GUESSNUMBER WHERE GAMEID = ?", new GameMapper(), gameId);
		}
		catch (EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Game addGame(Game game) {
		try {
			jdbcTemplate.update("INSERT INTO GUESSNUMBER VALUES (?,?,?)",game.getGameId(), game.getAnswer(), game.isStatus());
			return game;
		}catch (DataAccessException ex) {
			return  null;
		}
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
		final String UPDATE_GAME = "UPDATE GUESSNUMBER SET ANSWER = ? , STATUS= ? WHERE GAMEID = ?";
		return jdbcTemplate.update(UPDATE_GAME, game.getAnswer(),game.isStatus(), game.getGameId())>0;

	}

}
