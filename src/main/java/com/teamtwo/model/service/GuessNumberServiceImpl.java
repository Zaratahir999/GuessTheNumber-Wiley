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
		String answer = gameDao.getGamebyId(round.getGameId()).getAnswer();
		String guess = round.getGuess();
//		Round round2= determineResult(round,guess, answer);

		if (guess.equals(answer)) {
			Game game = getGameById(round.getGameId());
			game.setStatus(true);
			game.setAnswer(answer);
			gameDao.updateGame(game);
		}
		setTimeStamp(round2);
		return roundDao.addRound(round2);
	}


	@Override
	public Game startGame() {
		Game game = new Game();
		game.setAnswer(getRandom4DigitNumber()); // set the randomly generated answer for the game
		game = gameDao.addGame(game);

		return game.getGameId();
		return null;
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


