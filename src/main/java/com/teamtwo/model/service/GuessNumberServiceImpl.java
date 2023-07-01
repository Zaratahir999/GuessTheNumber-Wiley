package com.teamtwo.model.service;

import java.util.*;

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

	private final String REDACTED = "****";

	@Override
	public Game addGame(Game game) {
		return gameDao.addGame(game);
	}

	public Game getGameById(int gameId) {
		Game game = gameDao.getGamebyId(gameId);

		if (game!=null && !game.isFinished()) {
			game.setAnswer(REDACTED);
			return game;
		}

		return game;
	}

	public String beginNewGame() {
		Game newGame = new Game();
		newGame.setAnswer(generateAnswer());
		newGame = addGame(newGame);
		return "Game " + newGame.getGameId() + " has been created!";
	}

	public String generateAnswer() {
		Set<Integer> uniqueNum = new HashSet<>();
		Random rand = new Random();
		while (uniqueNum.size() < 4) {
			uniqueNum.add(rand.nextInt(10));
		}
		String answer = "";
		for (Integer num : uniqueNum) {
			answer += num;
		}
		return answer;
	}
	public List<Game> getAllGames() {
		List<Game> games = gameDao.getAllGames();

		for (Game game : games) {
			if (!game.isFinished()) {
				game.setAnswer(REDACTED);
			}
		}
		return games;
	}


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
			game.setFinished(true);
			gameDao.updateGame(game);
		}

		//set result and add round to storage
		round.setResult(result);

		round = addRound(round);

		return round;
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


