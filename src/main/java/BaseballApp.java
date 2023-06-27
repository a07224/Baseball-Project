import db.DBConnection;
import dto.StadiumRequestDTO;
import model.stadium.Stadium;
import model.stadium.StadiumDAO;
import model.team.Team;
import model.team.TeamDAO;
import service.StadiumService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BaseballApp {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getInstance();
        StadiumService stadiumService = new StadiumService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");
        String input = scanner.next();
        if(input.equals("야구장목록")){
            List<Stadium> stadiumLsit = stadiumService.AllStadiumList();
            System.out.println(stadiumLsit);
        }
        if(input.equals("팀목록")){
            //팀리스트 출력
            return;
        }
        String[] str = input.split("\\?");
        if(str[0].equals("야구장등록")){
            String[] temp = str[1].split("=");
            StadiumRequestDTO stadiumRequestDTO = new StadiumRequestDTO();
            stadiumRequestDTO.setName(temp[1]);
            stadiumService.createStadium(stadiumRequestDTO.getName());
            System.out.println(stadiumRequestDTO.getName());
        }
        if(str[0].equals("팀등록")){
            String[] temp = str[1].split("&");
            String[] stadiumIdsplit = temp[0].split("=");
            String[] teamNamesplit = temp[1].split("=");

        }


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

        //TeamDAO teamDAO = new TeamDAO(connection);
//        try {
//            teamDAO.createTeam(1,"ssg랜더스");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        try {
//            List<Team> teamList = teamDAO.getAllTeam();
//            System.out.println(teamList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
