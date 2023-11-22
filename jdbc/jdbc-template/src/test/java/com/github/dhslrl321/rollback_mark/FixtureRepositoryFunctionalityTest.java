package com.github.dhslrl321.rollback_mark;

import com.github.dhslrl321.rollback_mark.fixture.ApplicationContext4Test;
import com.github.dhslrl321.soccer.domain.Player;
import com.github.dhslrl321.soccer.domain.PlayerRepository;
import com.github.dhslrl321.soccer.domain.Team;
import com.github.dhslrl321.soccer.domain.TeamRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ApplicationContext4Test.class)
public class FixtureRepositoryFunctionalityTest {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerRepository playerRepository;

    @Test
    @DisplayName("repository functionality test")
    void name() {
        String pId = genId();
        String tId = genId();
        Player player = new Player(pId, tId, "장원익");
        Team team = new Team(tId, "리버풀");

        playerRepository.save(player);
        assertThat(playerRepository.findById(pId)).isEqualTo(player);

        teamRepository.save(team);
        assertThat(teamRepository.findById(tId)).isEqualTo(team);
    }

    private static String genId() {
        return UUID.randomUUID().toString();
    }

}
