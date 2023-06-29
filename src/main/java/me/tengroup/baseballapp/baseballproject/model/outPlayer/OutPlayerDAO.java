package me.tengroup.baseballapp.baseballproject.model.outPlayer;

import me.tengroup.baseballapp.baseballproject.data.domain.OutPlayerRequestDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {
    private Connection connection;
    public OutPlayerDAO(Connection connection){
        this.connection = connection;
    }

    public int insertOutPlayer(int playerId, String reason) throws SQLException {
        int result = 0;
        String query = "INSERT INTO out_player_tb (player_id, reason, out_player_created_at) VALUES (?, ?, now())";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,playerId);
            statement.setString(2, reason);
            result = statement.executeUpdate();
        }
        return result;
    }

    public List<OutPlayerRequestDTO.OutPlayerSelectDTO> getAllOutPlayer() throws SQLException{
        List<OutPlayerRequestDTO.OutPlayerSelectDTO> outPlayers = new ArrayList<>();
        String query = "SELECT player_tb.id, player_tb.name, player_tb.position, out_player_tb.reason, out_player_tb.out_player_created_at FROM player_tb left outer join out_player_tb ON out_player_tb.player_id=player_tb.id";
        try(Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)){
                while (resultSet.next()){
                    OutPlayerRequestDTO.OutPlayerSelectDTO outPlayerSelectDTO = buildOutPlayerFromResultSet(resultSet);
                    outPlayers.add(outPlayerSelectDTO);
                }
            }
        }
        return outPlayers;
    }

    private OutPlayerRequestDTO.OutPlayerSelectDTO buildOutPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String position = resultSet.getString("position");
        String reason = resultSet.getString("reason");
        Timestamp outPlayerCreatedAt = resultSet.getTimestamp("out_player_created_at");
        return OutPlayerRequestDTO.OutPlayerSelectDTO.builder()
                .id(id)
                .name(name)
                .position(position)
                .reason(reason)
                .outPlayerCreatedAt(outPlayerCreatedAt)
                .build();
    }

}
