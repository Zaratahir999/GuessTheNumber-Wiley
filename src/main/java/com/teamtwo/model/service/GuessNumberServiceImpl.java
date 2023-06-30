package com.teamtwo.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	@Autowired
	private RoundDao roundDao;


	@Override
	public List<Game> getAllGames() {
		return gameDao.getAllGames();
	}

	@Override
	public Game getGameById(int gameId) {
		return gameDao.getGamebyId(gameId);
	}

	@Override
	public Game addGame(Game game) {
		return gameDao.addGame(game);
	}

//	@Override
//	public Game updateGame(Game game) {
//		if(gameDao.updateGame(status, gameId)>0)
//			return getGameById(gameId);
//		return null;;
//	}

	@Override
	public List<Round> getAllRoundsbyGameId(int gameId) {
		return roundDao.getAllRoundsByGameId(gameId);
	}

	@Override
	public Round getRound(int roundId) {
		return roundDao.getRound(roundId);
	}

	@Override
	public Round addRound(Round round) {
		return roundDao.addRound(round);
	}

	@Override
	public Round guess(Round round) {
		Game game = gameDao.getGamebyId(round.getGameId());
		String answer = game.getAnswer();
		String guess = round.getGuess();

		String result = getGameResults(guess, answer);

		if(result.equals("e:4:p:0")) {
			game.setStatus(true);
			gameDao.updateGame(game);
		}

		//set result and add round to storage
		round.setResult(result);

		round = roundDao.addRound(round);

		return round;
	}

	public String getNewAnswer() {
		Random rand = new Random();
		String randString = "";
		for(int i=0; i<4; i++) {
			int randNumber = rand.nextInt(10);
			while(randString.contains(randNumber+"")) {
				randNumber = rand.nextInt(10);
			}
			randString += randNumber;
		}
		return randString;
	}

	@Override
	public Game startGame() {
		Game newGame = new Game();
		newGame.setStatus(false);
		newGame.setAnswer(getNewAnswer());
		newGame = gameDao.addGame(newGame);

		return newGame;
	}

	@Override
	public String getGameResults(String guess, String answer) {
		char[] guessCharacters = guess.toCharArray();
		char[] answerCharacters = answer.toCharArray();

		int exactMatch = 0;
		int partialMatch = 0;

		for (int i = 0; i < guessCharacters.length; i++) {

			if (answer.indexOf(guessCharacters[i]) > -1) {
				if (guessCharacters[i] == answerCharacters[i]) {
					exactMatch++;
				} else {
					partialMatch++;
				}
			}
		}

		return "e:" + exactMatch + ":p:" + partialMatch;
	}
}


