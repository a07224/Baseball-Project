package me.tengroup.baseballapp.baseballproject.model.team;

import me.tengroup.baseballapp.baseballproject.data.dto.TeamRequestDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private Connection connection;

    public TeamDAO(Connection connection) {
        this.connection = connection;
    }

    public int createTeam(int stadiumId, String teamName)throws SQLException {
        int result = 0;
        String query = "INSERT INTO team_tb (stadium_id, team_name, team_created_at) VALUES (?, ?, now())";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,stadiumId);
            statement.setString(2, teamName);
            result = statement.executeUpdate();
        }
        return result;
    }

    public List<TeamRequestDTO.TeamSelectReqDTO> getAllTeam() throws SQLException{
        List<TeamRequestDTO.TeamSelectReqDTO> teams = new ArrayList<>();
        String query = "SELECT team_tb.id, stadium_tb.name, team_tb.team_name FROM team_tb JOIN stadium_tb ON team_tb.stadium_id = stadium_tb.stadium_id;";
        try(Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)){
                while (resultSet.next()){
                    TeamRequestDTO.TeamSelectReqDTO teamSelectReqDTO = buildTeamFromResultSet(resultSet);
                    teams.add(teamSelectReqDTO);
                }
            }
        }
        return teams;
    }

    public Team findTeamNameById(int id) throws SQLException{
        String query = "SELECT * FROM team_tb where id=?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    return Team.builder()
                            .id(resultSet.getInt("id"))
                            .stadiumId(resultSet.getInt("stadium_id"))
                            .teamName(resultSet.getString("team_name"))
                            .teamCreatedAt(resultSet.getTimestamp("team_created_at"))
                            .build();
                }
            }
        }catch (Exception e){
            System.out.println("x");
        }
        return null;
    }

    private TeamRequestDTO.TeamSelectReqDTO buildTeamFromResultSet(ResultSet resultSet) throws SQLException{
        int stadiumId = resultSet.getInt("id");
        String stadiumName = resultSet.getString("name");
        String teamName = resultSet.getString("team_name");
        return TeamRequestDTO.TeamSelectReqDTO.builder()
                .id(stadiumId)
                .stadiumName(stadiumName)
                .teamName(teamName)
                .build();
    }
}
