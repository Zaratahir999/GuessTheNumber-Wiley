package com.teamtwo.controller.resource;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

	//	"game/{gameId}" - GET – Returns a specific game based on ID. Be sure in-progress games do not display their answer.
	@GetMapping(path = "/game/{gameId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Game> getGameById(@PathVariable("gameId") int gameId) {
		Game game = guessNumberService.getGameById(gameId);
		ResponseEntity<Game> response = null;
		if(game!=null)
			response=new ResponseEntity<Game>(game, HttpStatus.FOUND);
		else
			response=new ResponseEntity<Game>(game,HttpStatus.NOT_FOUND);
		return response;
	}

	//	"rounds/{gameId} – GET – Returns a list of rounds for the specified game sorted by time.
	@GetMapping(path = "/round/{gameId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Round> getAllRoundsByGameId(@PathVariable("gameId") int gameId) {
		List<Round> round = guessNumberService.getAllRoundsbyGameId(gameId);
		ResponseEntity<Round> response = null;
		if(round!=null)
			response=new ResponseEntity<Round>((Round) round, HttpStatus.FOUND);
		else
			response=new ResponseEntity<Round>((Round) round,HttpStatus.NOT_FOUND);
		return response;
	}

}
