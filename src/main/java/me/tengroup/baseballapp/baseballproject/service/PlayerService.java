package me.tengroup.baseballapp.baseballproject.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import me.tengroup.baseballapp.baseballproject.data.db.DBConnection;
import me.tengroup.baseballapp.baseballproject.model.player.Player;
import me.tengroup.baseballapp.baseballproject.model.player.PlayerDAO;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerService {
    private Connection connection = DBConnection.getInstance();
    private PlayerDAO playerDAO = new PlayerDAO(connection);
    private EntityManager em;

    public int insertPlayer(int teamId, String name, String position) throws SQLException {
        return playerDAO.insertPlayer(teamId, name, position);
    }
    public List<Player> getPlayerByTeamId(int teamId) throws SQLException {
        return playerDAO.getPlayerByTeamId(teamId);
    }

    @Transactional
    public Query positionQuery() {
            StringBuffer mb = new StringBuffer();
            mb.append("select p.position as 'position', ");
            mb.append("GROUP_CONCAT((CASE WHEN p.teamId = 11 THEN p.playerName ELSE null END)) as 'lotte', ");
            mb.append("from player p GROUP BY p.position");
            Query query = (Query) em.createNativeQuery(mb.toString());
            return query;
    }
}
