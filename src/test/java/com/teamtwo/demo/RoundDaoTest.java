package com.teamtwo.demo;

import com.teamtwo.entity.Round;
import com.teamtwo.model.persistence.RoundDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class RoundDaoTest {

        @Autowired
        private RoundDao roundDao;

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @BeforeEach
        void setUp() {
                clearRoundTable();
        }

        @Test
        void testGetAllRoundsByGameId() {
                int gameId = 1;

                List<Round> rounds = roundDao.getAllRoundsByGameId(gameId);

                Assertions.assertEquals(0, rounds.size());
        }

        private void clearRoundTable() {
                jdbcTemplate.update("DELETE FROM ROUND");
        }
}