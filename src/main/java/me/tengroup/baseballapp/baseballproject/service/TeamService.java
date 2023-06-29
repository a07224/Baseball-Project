package me.tengroup.baseballapp.baseballproject.service;

import lombok.RequiredArgsConstructor;
import me.tengroup.baseballapp.baseballproject.data.db.DBConnection;
import me.tengroup.baseballapp.baseballproject.data.dto.TeamRequestDTO;
import me.tengroup.baseballapp.baseballproject.model.team.Team;
import me.tengroup.baseballapp.baseballproject.model.team.TeamDAO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@RequiredArgsConstructor
@Service
public class TeamService {
    private Connection connection = DBConnection.getInstance();
    private TeamDAO teamDAO = new TeamDAO(connection);

    public int createTeam(int stadiumID, String name) throws SQLException {
        return teamDAO.createTeam(stadiumID, name);
    }

    public List<TeamRequestDTO.TeamSelectReqDTO> AllTeamList() throws SQLException {
        return teamDAO.getAllTeam();
    }

    public Team findTeamNameById(int id) throws SQLException {
        return teamDAO.findTeamNameById(id);
    }
}
