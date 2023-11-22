package com.github.dhslrl321.soccer.application;

import com.github.dhslrl321.soccer.domain.Player;
import com.github.dhslrl321.soccer.domain.PlayerRepository;
import com.github.dhslrl321.soccer.domain.Team;
import com.github.dhslrl321.soccer.domain.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OpenTeamService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public void open(Player player, String teamName) {

        String tId = genId();
        player.setTeamId(tId);

        playerRepository.save(player);

        teamRepository.save(new Team(tId, teamName));
    }

    private static String genId() {
        return UUID.randomUUID().toString();
    }
}
