package com.github.dhslrl321.soccer.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlayerRepository {

    private final JdbcTemplate jdbcTemplate;

    public Player findById(String id) {
        String query = "SELECT * FROM players WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, new PlayerRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public List<Player> findAll() {
        String query = "SELECT * FROM players";
        return jdbcTemplate.query(query, new PlayerRowMapper());
    }

    public void save(Player player) {
        try {
            Player existingPlayer = findById(player.getId());

            if (existingPlayer == null) {
                // 존재하지 않으면 INSERT
                String insertQuery = "INSERT INTO players (ida, team_id, name) VALUES (?, ?, ?)";
                jdbcTemplate.update(insertQuery, player.getId(), player.getTeamId(), player.getName());
            } else {
                // 존재하면 UPDATE
                String updateQuery = "UPDATE players SET team_id = ?, name = ? WHERE id = ?";
                jdbcTemplate.update(updateQuery, player.getTeamId(), player.getName(), player.getId());
            }

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    private static class PlayerRowMapper implements RowMapper<Player> {
        @Override
        public Player mapRow(ResultSet resultSet, int i) throws SQLException {
            Player player = new Player();
            player.setId(resultSet.getString("id"));
            player.setTeamId(resultSet.getString("team_id"));
            player.setName(resultSet.getString("name"));
            return player;
        }
    }
}
