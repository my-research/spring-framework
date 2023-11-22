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
public class TeamRepository {

    private final JdbcTemplate jdbcTemplate;

    public Team findById(String id) {
        String query = "SELECT * FROM teams WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, new TeamRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public List<Team> findAll() {
        String query = "SELECT * FROM teams";
        return jdbcTemplate.query(query, new TeamRowMapper());
    }

    public void save(Team team) {
        Team existingTeam = findById(team.getId());

        if (existingTeam == null) {
            // 존재하지 않으면 INSERT
            String insertQuery = "INSERT INTO teams (id, name) VALUES (?, ?)";
            jdbcTemplate.update(insertQuery, team.getId(), team.getName());
        } else {
            // 존재하면 UPDATE
            String updateQuery = "UPDATE teams SET name = ? WHERE id = ?";
            jdbcTemplate.update(updateQuery, team.getName(), team.getId());
        }
    }

    private static class TeamRowMapper implements RowMapper<Team> {
        @Override
        public Team mapRow(ResultSet resultSet, int i) throws SQLException {
            Team team = new Team();
            team.setId(resultSet.getString("id"));
            team.setName(resultSet.getString("name"));
            return team;
        }
    }
}
