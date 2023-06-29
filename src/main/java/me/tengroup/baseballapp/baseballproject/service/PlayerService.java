package me.tengroup.baseballapp.baseballproject.service;

import me.tengroup.baseballapp.baseballproject.data.db.DBConnection;
import me.tengroup.baseballapp.baseballproject.model.player.Player;
import me.tengroup.baseballapp.baseballproject.model.player.PlayerDAO;

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
