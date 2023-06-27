import db.DBConnection;
import dto.StadiumRequestDTO;
import dto.TeamRequestDTO;
import model.stadium.Stadium;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BaseballApp {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getInstance();
        StadiumService stadiumService = new StadiumService();
        TeamService teamService = new TeamService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("어떤 기능을 요청하시겠습니까?");
        String input = scanner.next();
        if(input.equals("야구장목록")){
            List<Stadium> stadiumLsit = stadiumService.AllStadiumList();
            int i=0;
            for(Stadium data: stadiumLsit){
                System.out.println(data.getStadiumId() +". "+data.getName());
                i++;
            }
        }
        if(input.equals("팀목록")){
            List<TeamRequestDTO.TeamSelectReqDTO> teamList = teamService.AllTeamList();
            for(TeamRequestDTO.TeamSelectReqDTO data: teamList){
                System.out.println(data.getId()+". "+data.getStadiumName()+" "+data.getTeamName());

            }
        }
        String[] str = input.split("\\?");
        if(str[0].equals("야구장등록")){
            String[] temp = str[1].split("=");
            StadiumRequestDTO stadiumRequestDTO = new StadiumRequestDTO();
            stadiumRequestDTO.setName(temp[1]);
            int result = stadiumService.createStadium(stadiumRequestDTO.getName());
            if(result == 1){
                System.out.println("성공");
            }
        }
        if(str[0].equals("팀등록")){
            String[] temp = str[1].split("&");
            String[] stadiumIdsplit = temp[0].split("=");
            String[] teamNamesplit = temp[1].split("=");
            TeamRequestDTO.TeamInsertReqDTO teamInsertReqDTO = new TeamRequestDTO.TeamInsertReqDTO();
            teamInsertReqDTO.setStadiumId(Integer.parseInt(stadiumIdsplit[1]));
            teamInsertReqDTO.setTeamName(teamNamesplit[1]);
            int result = teamService.createTeam(teamInsertReqDTO.getStadiumId(), teamInsertReqDTO.getTeamName());
            if(result == 1){
                System.out.println("성공");
            }
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
