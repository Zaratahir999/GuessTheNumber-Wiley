package com.teamtwo.controller.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.teamtwo.model.service.GuessNumberService;

@RestController
public class GuessNumberResource {

	@Autowired
	private GuessNumberService guessNumberService;
}
