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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Round getRound(int roundId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Round addRound(Round round) {
		// TODO Auto-generated method stub
		return null;
	
	}

}
