import db.DBConnection;
import model.stadium.Stadium;
import model.stadium.StadiumDAO;
import model.team.Team;
import model.team.TeamDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BaseballApp {
    public static void main(String[] args) {
        Connection connection = DBConnection.getInstance();
      //  StadiumDAO stadiumDAO = new StadiumDAO(connection);
//        try {
//            stadiumDAO.createStaduim("잠실");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        try {
//            List<Stadium> stadiumList = stadiumDAO.getAllStadium();
//            System.out.println(stadiumList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        TeamDAO teamDAO = new TeamDAO(connection);
//        try {
//            teamDAO.createTeam(1,"ssg랜더스");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
        try {
            List<Team> teamList = teamDAO.getAllTeam();
            System.out.println(teamList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
