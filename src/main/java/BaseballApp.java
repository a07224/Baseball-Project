import db.DBConnection;
import dto.OutPlayerRequestDTO;
import dto.PlayerRequestDTO;
import dto.StadiumRequestDTO;
import dto.TeamRequestDTO;
import model.outPlayer.OutPlayer;
import model.player.Player;
import model.stadium.Stadium;
import model.team.Team;
import service.OutPlayerService;
import service.PlayerService;
import service.StadiumService;
import service.TeamService;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class BaseballApp {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getInstance();
        StadiumService stadiumService = new StadiumService();
        TeamService teamService = new TeamService();
        PlayerService playerService = new PlayerService();
        OutPlayerService outPlayerService = new OutPlayerService();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("===========================================");
            System.out.println("         어떤 기능을 요청하시겠습니까?");
            System.out.println("===========================================");
            System.out.println("1. 야구장목록");
            System.out.println("2. 팀목록");
            System.out.println("3. 선수목록?teamId={팀아이디}");
            System.out.println("4. 퇴출목록");
            System.out.println("5. 포지션별목록");
            System.out.println("6. 야구장등록?name={야구장이름}");
            System.out.println("7. 선수등록?teamId={팀아이디}&name={선수이름}&position={포지션}");
            System.out.println("8. 퇴출등록?playerId={선수아이디}&reason={이유}");
            System.out.println("9. 종료");
            System.out.println("===========================================");
            String input = scanner.next();

            if(input.equals("종료")){
                System.out.println("기능을 종료합니다.");
                break;
            }

            if (input.equals("야구장목록")) {
                List<Stadium> stadiumLsit = stadiumService.AllStadiumList();
                int i = 0;
                System.out.println("===========================");
                System.out.println("    번호          야구장     ");
                System.out.println("===========================");
                for (Stadium data : stadiumLsit) {
                    System.out.println("     " + data.getStadiumId() + ".      " + data.getName());
                    i++;
                }
            }

            if (input.equals("팀목록")) {
                List<TeamRequestDTO.TeamSelectReqDTO> teamList = teamService.AllTeamList();
                int i = 1;
                System.out.println("===========================");
                System.out.println("번호      홈구장       팀이름");
                System.out.println("===========================");
                for (TeamRequestDTO.TeamSelectReqDTO data : teamList) {
                    //System.out.println(i+".     "+data.getStadiumName()+"    "+data.getTeamName());
                    System.out.printf("%d. %10s %7s \n", i, data.getStadiumName(), data.getTeamName());
                    i++;
                }

            }

            if (input.equals("퇴출목록")) {
                List<OutPlayerRequestDTO.OutPlayerSelectDTO> outPlayerList = outPlayerService.selectOutPlayer();
                System.out.println("===========================================");
                System.out.println("번호   이름     포지션     이유       날짜");
                System.out.println("===========================================");
                for (OutPlayerRequestDTO.OutPlayerSelectDTO data : outPlayerList) {
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(data.getOutPlayerCreatedAt());
                    System.out.println(data.getId() + ".    " + data.getName() + "    " + data.getPosition() + "     " + data.getReason() + "    " + date);
                }
            }

            String[] str = input.split("\\?");
            if (str[0].equals("선수목록")) {
                String[] temp = str[1].split("=");
                Team team = teamService.findTeamNameById(Integer.parseInt(temp[1]));
                String teamName = team.getTeamName();
                System.out.println("[" + teamName + "의 선수목록]");
                System.out.println("===========================");
                System.out.println("번호        이름       포지션");
                System.out.println("===========================");
                List<Player> playerList = playerService.getPlayerByTeamId(Integer.parseInt(temp[1]));
                for (Player data : playerList) {
                    System.out.println(data.getId() + ".        " + data.getName() + "       " + data.getPosition());
                }
            }

            if (str[0].equals("야구장등록")) {
                String[] temp = str[1].split("=");
                StadiumRequestDTO stadiumRequestDTO = new StadiumRequestDTO();
                stadiumRequestDTO.setName(temp[1]);
                int result = stadiumService.createStadium(stadiumRequestDTO.getName());
                if (result == 1) {
                    System.out.println("성공");
                }
            }

            if (str[0].equals("팀등록")) {
                String[] temp = str[1].split("&");
                String[] stadiumIdsplit = temp[0].split("=");
                String[] teamNamesplit = temp[1].split("=");
                TeamRequestDTO.TeamInsertReqDTO teamInsertReqDTO = new TeamRequestDTO.TeamInsertReqDTO();
                teamInsertReqDTO.setStadiumId(Integer.parseInt(stadiumIdsplit[1]));
                teamInsertReqDTO.setTeamName(teamNamesplit[1]);
                int result = teamService.createTeam(teamInsertReqDTO.getStadiumId(), teamInsertReqDTO.getTeamName());
                if (result == 1) {
                    System.out.println("성공");
                }
            }

            if (str[0].equals("선수등록")) {
                String[] temp = str[1].split("&");
                String[] teamIdsplit = temp[0].split("=");
                String[] playerNamesplit = temp[1].split("=");
                String[] playerPositionsplit = temp[2].split("=");
                PlayerRequestDTO playerRequestDTO = new PlayerRequestDTO();
                playerRequestDTO.setTeamId(Integer.parseInt(teamIdsplit[1]));
                playerRequestDTO.setName(playerNamesplit[1]);
                playerRequestDTO.setPosition(playerPositionsplit[1]);
                int result = playerService.insertPlayer(playerRequestDTO.getTeamId(), playerRequestDTO.getName(), playerRequestDTO.getPosition());
                if (result == 1) {
                    System.out.println("성공");
                }
            }

            if (str[0].equals("퇴출등록")) {
                String[] temp = str[1].split("&");
                String[] playerIdsplit = temp[0].split("=");
                String[] reasonsplit = temp[1].split("=");
                OutPlayerRequestDTO.OutPlayerInsertDTO outPlayerInsertDTO = new OutPlayerRequestDTO.OutPlayerInsertDTO();
                outPlayerInsertDTO.setPlayerId(Integer.parseInt(playerIdsplit[1]));
                outPlayerInsertDTO.setReason(reasonsplit[1]);
                int result = outPlayerService.insertOutPlayer(outPlayerInsertDTO.getPlayerId(), outPlayerInsertDTO.getReason());
                if (result == 1) {
                    System.out.println("성공");
                }
            }
        }

    }
}
