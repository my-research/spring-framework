package com.github.dhslrl321.rollback_mark;

import com.github.dhslrl321.rollback_mark.fixture.ApplicationContext4Test;
import com.github.dhslrl321.soccer.domain.Player;
import com.github.dhslrl321.soccer.domain.PlayerRepository;
import com.github.dhslrl321.soccer.domain.Team;
import com.github.dhslrl321.soccer.domain.TeamRepository;
import com.github.dhslrl321.soccer.application.OpenTeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = ApplicationContext4Test.class)
public class AbortTest {

    @Autowired
    OpenTeamService openTeamService;

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    TeamRepository teamRepository;

    @Test
    void name() {
        openTeamService.open(new Player("jangwonik"), "리버풀");

        List<Player> players = playerRepository.findAll();
        List<Team> teams = teamRepository.findAll();
        System.out.println("all = " + players);
    }
}
