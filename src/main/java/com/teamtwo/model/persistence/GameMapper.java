package com.teamtwo.model.persistence;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.teamtwo.entity.Game;
public class GameMapper implements RowMapper<Game> {
    @Override
    public Game mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Game game = new Game();

        game.setGameId(resultSet.getInt("GAMEID"));
        game.setAnswer(resultSet.getString("ANSWER"));
        game.setFinished(resultSet.getBoolean("FINISHED"));

        return game;
    }
}