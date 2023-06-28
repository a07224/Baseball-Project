package model.outPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
