package model.team;

import model.stadium.Stadium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private Connection connection;

    public TeamDAO(Connection connection) {
        this.connection = connection;
    }

    public void createTeam(int stadiumId, String teamName)throws SQLException {
        String query = "INSERT INTO team_tb (stadium_id, team_name, team_created_at) VALUES (?, ?, now())";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,stadiumId);
            statement.setString(2, teamName);
            statement.executeUpdate();
        }
    }

    public List<Team> getAllTeam() throws SQLException{
        List<Team> teams = new ArrayList<>();
        //조인은되는데 team에 stadium의 name이 없기때문에 출력되지않은것으로 보임
        String query = "SELECT team_tb.*, stadium_tb.* FROM team_tb JOIN stadium_tb ON team_tb.stadium_id = stadium_tb.stadium_id";
        try(Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)){
                while (resultSet.next()){
                    Team team = buildTeamFromResultSet(resultSet);
                    teams.add(team);
                }
            }
        }
        return teams;
    }

    private Team buildTeamFromResultSet(ResultSet resultSet) throws SQLException{
        String teamName = resultSet.getString("team_name");
        Timestamp teamCreatedAt = resultSet.getTimestamp("team_created_at");
        return Team.builder()
                .teamName(teamName)
                .teamCreatedAt(teamCreatedAt)
                .build();
    }
}
