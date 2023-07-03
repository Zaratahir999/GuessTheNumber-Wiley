package com.teamtwo.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import com.teamtwo.entity.Game;
import com.teamtwo.model.persistence.GameDao;

@SpringBootTest
@ActiveProfiles("test")
class GameDaoTest {

	@Autowired
	private GameDao gameDao;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void testGetAllGames() {
		clearGameTable();

		Game game1 = new Game(0, null, false);
		game1.setAnswer("1234");
		game1.setFinished(false);
		gameDao.addGame(game1);

		Game game2 = new Game(0, null, false);
		game2.setAnswer("5678");
		game2.setFinished(false);
		gameDao.addGame(game2);

		List<Game> games = gameDao.getAllGames();

		Assertions.assertEquals(2, games.size());
		boolean foundGame1 = false;
		boolean foundGame2 = false;

		for (Game game : games) {
			if (game.getAnswer().equals(game1.getAnswer()) && game.isFinished() == game1.isFinished()) {
				foundGame1 = true;
			} else if (game.getAnswer().equals(game2.getAnswer()) && game.isFinished() == game2.isFinished()) {
				foundGame2 = true;
			}
		}
		Assertions.assertTrue(foundGame1);
		Assertions.assertTrue(foundGame2);
	}

	private void clearGameTable() {
		jdbcTemplate.update("DELETE FROM GAME");
	}

	@Test
	public void testAddGetGame() {
		Game game = new Game(0, null, false);
		game.setAnswer("1234");
		game.setFinished(false);
		game = gameDao.addGame(game);

		Game fromDao = gameDao.getGamebyId(game.getGameId());

		assertEquals(game.getGameId(), fromDao.getGameId());
		assertEquals(game.getAnswer(), fromDao.getAnswer());
		assertEquals(game.isFinished(), fromDao.isFinished());
	}

	@Test
	public void testUpdateGame() {
		Game game = new Game(0, null, false);
		game.setAnswer("1234");
		game.setFinished(false);
		game = gameDao.addGame(game);

		Game fromDao = gameDao.getGamebyId(game.getGameId());

		assertEquals(game.getGameId(), fromDao.getGameId());
		assertEquals(game.getAnswer(), fromDao.getAnswer());
		assertEquals(game.isFinished(), fromDao.isFinished());

		game.setFinished(true);

		gameDao.updateGame(game);

		fromDao = gameDao.getGamebyId(game.getGameId());

		assertEquals(game.isFinished(), fromDao.isFinished());
	}
}