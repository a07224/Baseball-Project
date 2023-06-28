import db.DBConnection;
import dto.PlayerRequestDTO;
import dto.StadiumRequestDTO;
import dto.TeamRequestDTO;
import model.player.Player;
import model.stadium.Stadium;
import model.team.Team;
import service.PlayerService;
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
        PlayerService playerService = new PlayerService();
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
            int i=1;
            for(TeamRequestDTO.TeamSelectReqDTO data: teamList){
                System.out.println(i+". "+data.getStadiumName()+" "+data.getTeamName());
                i++;
            }
        }
        String[] str = input.split("\\?");
        if(str[0].equals("선수목록")){
            String[] temp = str[1].split("=");
            Team team = teamService.findTeamNameById(Integer.parseInt(temp[1]));
            String teamName = team.getTeamName();
            System.out.println(teamName+"의 선수목록입니다.");
            List<Player> playerList = playerService.getPlayerByTeamId(Integer.parseInt(temp[1]));
            for(Player data: playerList){
                System.out.println(data.getId()+". "+data.getName()+" "+data.getPosition());
            }
        }
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
        if(str[0].equals("선수등록")){
            String[] temp = str[1].split("&");
            String[] teamIdsplit = temp[0].split("=");
            String[] playerNamesplit = temp[1].split("=");
            String[] playerPositionsplit = temp[2].split("=");
            PlayerRequestDTO playerRequestDTO = new PlayerRequestDTO();
            playerRequestDTO.setTeamId(Integer.parseInt(teamIdsplit[1]));
            playerRequestDTO.setName(playerNamesplit[1]);
            playerRequestDTO.setPosition(playerPositionsplit[1]);
            int result = playerService.insertPlayer(playerRequestDTO.getTeamId(), playerRequestDTO.getName(), playerRequestDTO.getPosition());
            if(result == 1){
                System.out.println("성공");
            }
        }

    }
}
