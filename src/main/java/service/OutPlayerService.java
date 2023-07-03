package service;

import db.DBConnection;
import dto.OutPlayerRequestDTO;
import model.outPlayer.OutPlayerDAO;
import model.player.PlayerDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OutPlayerService {
    private Connection connection = DBConnection.getInstance();
    private OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);
    private PlayerDAO playerDAO = new PlayerDAO(connection);
    public int insertOutPlayer(int playerId, String reason) throws SQLException {
        playerDAO.updatePlayer(playerId);
        return outPlayerDAO.insertOutPlayer(playerId, reason);
    }

    public List<OutPlayerRequestDTO.OutPlayerSelectDTO> selectOutPlayer() throws SQLException{
        return outPlayerDAO.getAllOutPlayer();
    }
}
