package com.teamtwo.model.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
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

    private final String REDACTED = "****";

    public List<Game> getAllGames() {
        List<Game> games = gameDao.getAllGames();

        for (Game game : games) {
            if (!game.isFinished()) {
                game.setAnswer(REDACTED);
            }
        }
        return games;
    }

    public Game getGameById(int gameId) {
        Game game = gameDao.getGamebyId(gameId);

        if (game != null && !game.isFinished()) {
            game.setAnswer(REDACTED);
            return game;
        }
        return game;
    }

    @Override
    public Game addGame(Game game) {
        return gameDao.addGame(game);
    }

    public boolean updateGame(Game game) {
        return gameDao.updateGame(game);
    }

    @Override
    public List<Round> getAllRoundsbyGameId(int gameId) {
        return roundDao.getAllRoundsByGameId(gameId);
    }

    @Override
    public Round addRound(Round round) {
        return roundDao.addRound(round);
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

    @Override
    public Round guess(Round round) {
        Game game = gameDao.getGamebyId(round.getGameId());
        String answer = game.getAnswer();
        String guess = round.getGuess();
        String result = getGameResults(guess, answer);

        round.setResult(result);
        round.setGuessTime(new Timestamp(System.currentTimeMillis()));
        round = addRound(round);

        if (result.equals("e:4:p:0")) {
            game.setFinished(true);
            updateGame(game);
        }
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