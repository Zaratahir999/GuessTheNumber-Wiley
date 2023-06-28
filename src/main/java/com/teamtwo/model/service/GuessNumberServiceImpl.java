package com.teamtwo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamtwo.entity.Game;
import com.teamtwo.entity.Round;
import com.teamtwo.model.persistence.GameDao;
import com.teamtwo.model.persistence.RoundDao;

@Service
public class GuessNumberServiceImpl implements GuessNumberService {
	
	@Autowired
	private GameDao gameDao;
	private RoundDao roundDao;
	

	@Override
	public List<Game> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game getGameById(int gameId) {
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

	@Override
	public List<Round> getAllRoundsbyGameId(int gameId) {
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

	@Override
	public Round guess(int gameId, String guess) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game startGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGameResults(String guess, String answer) {
		// TODO Auto-generated method stub
		return null;
	}

}
