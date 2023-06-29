package me.tengroup.baseballapp.baseballproject.service;

import me.tengroup.baseballapp.baseballproject.data.db.DBConnection;
import me.tengroup.baseballapp.baseballproject.data.domain.OutPlayerRequestDTO;
import me.tengroup.baseballapp.baseballproject.model.outPlayer.OutPlayerDAO;
import me.tengroup.baseballapp.baseballproject.model.player.PlayerDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OutPlayerService {
    private Connection connection = DBConnection.getInstance();
    private OutPlayerDAO outPlayerDAO = new OutPlayerDAO(connection);
    private PlayerDAO playerDAO = new PlayerDAO(connection);
    public int insertOutPlayer(int playerId, String reason) throws SQLException {
        int result = playerDAO.updatePlayer(playerId);
        if(result == 0){
            System.out.println("업데이트 실패");
            return 0;
        }
        return outPlayerDAO.insertOutPlayer(playerId, reason);
    }

    public List<OutPlayerRequestDTO.OutPlayerSelectDTO> selectOutPlayer() throws SQLException{
        return outPlayerDAO.getAllOutPlayer();
    }
}
