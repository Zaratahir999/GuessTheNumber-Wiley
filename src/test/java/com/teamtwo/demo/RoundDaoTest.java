package com.teamtwo.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.teamtwo.entity.Game;
import com.teamtwo.entity.Round;
import com.teamtwo.model.persistence.GameDao;
import com.teamtwo.model.persistence.RoundDao;

@SpringBootTest
@ActiveProfiles("test")
class RoundDaoTest {
	@Autowired
	    RoundDao roundDao;
	    GameDao gameDao;
	@Test
	void testAddGetGetAll() {
        int gameId = 1;
        int roundId = 2;
        
        Game game = new Game(gameId, null, false);
        game.setAnswer("5678");
        game.setFinished(false);
        game = gameDao.addGame(game);
        
        Round round = new Round(roundId, gameId, null, null, null);
        round.setGuess("1234");
        round.setResult("e:0:p:0");
        round.setGameId(gameId);
        round.setRoundId(roundId);
        roundDao.addRound(round);

        Round round2 = new Round(roundId, gameId, null, null, null);
        round2.setGuess("5678");
        round2.setResult("e:4:p:0");
        round2.setGameId(gameId);
        round.setRoundId(roundId);
        roundDao.addRound(round2);

        List<Round> rounds = roundDao.getAllRoundsByGameId(gameId);

        assertEquals(2, rounds.size());
        assertNotNull(round = roundDao.getRound(round.getRoundId()));

}
}
