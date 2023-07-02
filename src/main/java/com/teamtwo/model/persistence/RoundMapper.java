package com.teamtwo.model.persistence;

import com.teamtwo.entity.Round;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundMapper implements RowMapper<Round>{
    @Override
    public Round mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Round round = new Round();

        round.setRoundId(resultSet.getInt("ROUNDID"));
        round.setGameId(resultSet.getInt("GAMEID"));
        round.setGuess(resultSet.getString("GUESS"));
        round.setResult(resultSet.getString("RESULT"));
        round.setGuessTime(resultSet.getTimestamp("GUESSTIME"));

        return round;
    }
}