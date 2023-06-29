package com.teamtwo.controller.resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.teamtwo.entity.Game;
import com.teamtwo.entity.GameList;
import com.teamtwo.entity.Round;
import com.teamtwo.model.service.GuessNumberService;
@RestController
public class GuessNumberResource {
	@Autowired
	private GuessNumberService guessNumberService;
	// “begin” - POST – Starts a game, generates an answer, and sets the correct status
	@PostMapping(path = "/begin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> addGameResource(@RequestBody Game game) {
		Game addedGame = guessNumberService.addGame(game);
		if (addedGame != null) {
			return new ResponseEntity<>(addedGame, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
	}
	//“guess” – POST – Makes a guess by passing the guess and gameId in as JSON.
	@PostMapping(path = "/guess", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Round> addRoundResource(@RequestBody Round round) {
		Round result = guessNumberService.addRound(round);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//“game” – GET – Returns a list of all games.
	@GetMapping(path = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GameList> getAllGamesResource() {
		List<Game> games = guessNumberService.getAllGames();
		GameList gameList = new GameList(games);
		if (!games.isEmpty()) {
			return new ResponseEntity<>(gameList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
