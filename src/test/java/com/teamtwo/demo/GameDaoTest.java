package com.teamtwo.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.teamtwo.entity.Game;
import com.teamtwo.model.persistence.GameDao;
@SpringBootTest
@ActiveProfiles("test")

class GameDaoTest {
	 @Autowired
	    GameDao gameDao;
	@Test
	void testGetAllGames() {
		Game game = new Game(0, null, false);
        game.setAnswer("1234");
        game.setStatus(false);
        gameDao.addGame(game);

        Game game2 = new Game(0, null, false);
        game2.setAnswer("5678");
        game2.setStatus(false);
        gameDao.addGame(game2);
        
        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(game2));
	}
	
	 @Test
	    public void testAddGetGame() {
	        Game game = new Game(0, null, false);
	        game.setAnswer("1234");
	        game.setStatus(false);
	        game = gameDao.addGame(game);

	        Game fromDao = gameDao.getGamebyId(game.getGameId());

	        assertEquals(game, fromDao);
	    }

	    @Test
	    public void testUpdateGame() {
	        Game game = new Game(0, null, false);
	        game.setAnswer("1234");
	        game.setStatus(false);
	        game = gameDao.addGame(game);

	        Game fromDao = gameDao.getGamebyId(game.getGameId());

	        assertEquals(game, fromDao);

	        game.setStatus(true);

	        gameDao.updateGame(game);

	        assertNotEquals(game, fromDao);

	        fromDao = gameDao.getGamebyId(game.getGameId());

	        assertEquals(game, fromDao);
	    }

}
