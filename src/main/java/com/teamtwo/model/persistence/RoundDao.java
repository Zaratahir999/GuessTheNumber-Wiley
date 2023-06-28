package com.teamtwo.model.persistence;

import java.util.List;

import com.teamtwo.entity.Round;

public interface RoundDao {
	
	List<Round> getAllRoundsByGameId (int gameId);
	Round getRound (int roundId);
	Round addRound (Round round);
	
	
	

}
