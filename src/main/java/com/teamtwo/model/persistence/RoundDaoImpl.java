package com.teamtwo.model.persistence;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.teamtwo.entity.Round;

@Repository
public class RoundDaoImpl implements RoundDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Round> getAllRoundsByGameId(int gameId) {
		return jdbcTemplate.query("SELECT * FROM ROUND where GAMEID=?", new RoundMapper(), gameId);
	}

	@Override
	public Round addRound(Round round) {
		try {
			jdbcTemplate.update("INSERT INTO ROUND(GAMEID, GUESS, RESULT, GUESSTIME) VALUES(?,?,?,?)",
					round.getGameId(),
					round.getGuess(),
					round.getResult(),
					round.getGuessTime());
			round.setRoundId(getLastRoundId());
			return round;
		} catch (Exception ex) {
			return null;
		}
	}

	private int getLastRoundId() {
		return jdbcTemplate.queryForObject("SELECT ROUNDID FROM ROUND ORDER BY ROUNDID DESC LIMIT 1", Integer.class);
	}
}