package me.tengroup.baseballapp.baseballproject.model.player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private Connection connection;
    public PlayerDAO(Connection connection){
        this.connection = connection;
    }

    public int insertPlayer(int teamId, String name, String position) throws SQLException {
        int result = 0;
        String query = "INSERT INTO player_tb (team_id, name, position, player_created_at) VALUES (?, ?, ?, now())";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,teamId);
            statement.setString(2, name);
            statement.setString(3, position);
            result = statement.executeUpdate();
        }
        return result;
    }
    public List<Player> getPlayerByTeamId(int teamId) throws SQLException{
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player_tb where team_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    Player player = buildPlayerFromResultSet(resultSet);
                    players.add(player);
                }
            }
        }
        return players;
    }

    public int updatePlayer(int playerId)throws SQLException{
        int result = 0;
        String query = "UPDATE player_tb SET team_id=null where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, playerId);
            result = statement.executeUpdate();
        }catch (Exception e){
            System.out.println("안됨?");
        }
        return result;
    }

    private Player buildPlayerFromResultSet(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id");
        int teamId = resultSet.getInt("team_id");
        String name = resultSet.getString("name");
        String position = resultSet.getString("position");
        Timestamp playerCreatedAt = resultSet.getTimestamp("player_created_at");
        return Player.builder()
                .id(id)
                .teamId(teamId)
                .name(name)
                .position(position)
                .playerCreatedAt(playerCreatedAt)
                .build();
    }
}
