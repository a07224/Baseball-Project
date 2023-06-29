package model.stadium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {
    private Connection connection;

    public StadiumDAO(Connection connection) {
        this.connection = connection;
    }

    public int createStaduim(String name)throws SQLException{
        int result = 0;
        String query = "INSERT INTO stadium_tb (name, stadium_created_at) VALUES (?, now())";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, name);
            result = statement.executeUpdate();
        }
        return result;
    }

    public List<Stadium> getAllStadium() throws SQLException{
        List<Stadium> stadiums = new ArrayList<>();
        String query = "SELECT * FROM stadium_tb";
        try(Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)){
                while (resultSet.next()){
                    Stadium stadium = buildStadiumFromResultSet(resultSet);
                    stadiums.add(stadium);
                }
            }
        }
        return stadiums;
    }

    private Stadium buildStadiumFromResultSet(ResultSet resultSet) throws SQLException{
        int stadiumId = resultSet.getInt("stadium_id");
        String name = resultSet.getString("name");
        Timestamp stadiumCreatedAt = resultSet.getTimestamp("stadium_created_at");
        return Stadium.builder()
                .stadiumId(stadiumId)
                .name(name)
                .stadiumCreatedAt(stadiumCreatedAt)
                .build();
    }
}
