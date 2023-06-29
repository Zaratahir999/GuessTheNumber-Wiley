package com.teamtwo.model.persistence;

import com.teamtwo.entity.Round;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoundMapper implements RowMapper<Round>{

    @Override
    public Round mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Round round = new Round();

        round.setRoundId(resultSet.getInt("roundId"));
        round.setGameId(resultSet.getInt("gameId"));
        round.setGuess(resultSet.getString("guess"));
        round.setResult(resultSet.getString("result"));
        Timestamp timestamp = resultSet.getTimestamp("guessTime");
        round.setGuessTime(timestamp.toLocalDateTime());
        return round;
    }
}
