package service;

import db.DBConnection;
import dto.TeamRequestDTO;
import model.stadium.Stadium;
import model.stadium.StadiumDAO;
import model.team.Team;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
