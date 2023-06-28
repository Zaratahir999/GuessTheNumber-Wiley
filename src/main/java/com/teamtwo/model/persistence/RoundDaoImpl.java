package com.teamtwo.model.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teamtwo.entity.Round;

@Repository
public class RoundDaoImpl implements RoundDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Round> getAllRoundsByGameId(int gameId) {
		return jdbcTemplate.query("SELECT * FROM round", new RoundRowMapper());
	}

	@Override
	public Round getRound(int roundId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM round WHERE roundId=?", new RoundRowMapper(), roundId);
		}
		catch(EmptyResultDataAccessException ex) {
			return null;
		}
	}

	@Override
	public Round addRound(Round round) {
		try {
			jdbcTemplate.update("INSERT INTO round VALUES(?,?,?,?,?)",
					round.getRoundId(),
					round.getGameId(),
					round.getGuess(),
					round.getResult(),
					round.getGuessTime());

			return round;
		} catch (Exception ex) {
			return null; // or handle the exception appropriately
		}
	}

}
