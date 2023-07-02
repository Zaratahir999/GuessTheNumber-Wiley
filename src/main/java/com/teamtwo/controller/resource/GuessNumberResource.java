package com.teamtwo.controller.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.teamtwo.entity.Game;
import com.teamtwo.entity.Round;
import com.teamtwo.model.service.GuessNumberService;

@RestController
public class GuessNumberResource {
	@Autowired
	private GuessNumberService guessNumberService;

	// “begin” - POST – Starts a game, generates an answer, and sets the correct status
	@PostMapping(path = "/begin")
	public ResponseEntity<String> beginGame() {
		return ResponseEntity.status(HttpStatus.CREATED).body(guessNumberService.beginNewGame());
	}

	// “guess” – POST – Makes a guess by passing the guess and gameId in as JSON.
	@PostMapping(path = "/guess", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Round> makeGuess(@RequestBody Round round) {
		Round result = guessNumberService.guess(round);
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// “game” – GET – Returns a list of all games.
	@GetMapping(path = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Game>> getAllGamesResource() {
		List<Game> games = guessNumberService.getAllGames();
		if (games!=null && !games.isEmpty()) {
			return new ResponseEntity<>(games, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	// "game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
	@GetMapping(path = "/game/{gameId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> getGameById(@PathVariable("gameId") int gameId) {
		Game game = guessNumberService.getGameById(gameId);
		if(game!=null)
			return new ResponseEntity<>(game, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	// "rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
	@GetMapping(path = "/round/{gameId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Round>> getAllRoundsByGameId(@PathVariable("gameId") int gameId) {
		List<Round> rounds = guessNumberService.getAllRoundsbyGameId(gameId);
		if(rounds!=null && !rounds.isEmpty())
			return new ResponseEntity<>(rounds, HttpStatus.FOUND);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}