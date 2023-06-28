package service;

import db.DBConnection;
import dto.PlayerRequestDTO;
import model.player.Player;
import model.player.PlayerDAO;
import model.stadium.StadiumDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlayerService {
    private Connection connection = DBConnection.getInstance();
    private PlayerDAO playerDAO = new PlayerDAO(connection);

    public int insertPlayer(int teamId, String name, String position) throws SQLException {
        return playerDAO.insertPlayer(teamId, name, position);
    }
    public List<Player> getPlayerByTeamId(int teamId) throws SQLException {
        return playerDAO.getPlayerByTeamId(teamId);
    }
}
