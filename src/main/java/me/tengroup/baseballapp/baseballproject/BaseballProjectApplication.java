package me.tengroup.baseballapp.baseballproject;

import me.tengroup.baseballapp.baseballproject.data.db.DBConnection;
import me.tengroup.baseballapp.baseballproject.data.dto.OutPlayerRequestDTO;
import me.tengroup.baseballapp.baseballproject.data.dto.PlayerRequestDTO;
import me.tengroup.baseballapp.baseballproject.data.dto.StadiumRequestDTO;
import me.tengroup.baseballapp.baseballproject.data.dto.TeamRequestDTO;
import me.tengroup.baseballapp.baseballproject.model.player.Player;
import me.tengroup.baseballapp.baseballproject.model.stadium.Stadium;
import me.tengroup.baseballapp.baseballproject.model.team.Team;
import me.tengroup.baseballapp.baseballproject.service.OutPlayerService;
import me.tengroup.baseballapp.baseballproject.service.PlayerService;
import me.tengroup.baseballapp.baseballproject.service.StadiumService;
import me.tengroup.baseballapp.baseballproject.service.TeamService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BaseballProjectApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(BaseballProjectApplication.class, args);

		Connection connection;
		connection = DBConnection.getInstance();
		StadiumService stadiumService = new StadiumService();
		TeamService teamService = new TeamService();
		PlayerService playerService = new PlayerService();
		OutPlayerService outPlayerService = new OutPlayerService();
		Scanner scanner;
		scanner = new Scanner(System.in);
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

		if(input.equals("퇴출목록")){
			List<OutPlayerRequestDTO.OutPlayerSelectDTO> outPlayerList = outPlayerService.selectOutPlayer();
			for(OutPlayerRequestDTO.OutPlayerSelectDTO data: outPlayerList){
				System.out.println(data.getId()+" "+data.getName()+" "+data.getPosition()+" "+data.getReason()+" "+data.getOutPlayerCreatedAt());
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

		if(str[0].equals("퇴출등록")){
			String[] temp = str[1].split("&");
			String[] playerIdsplit = temp[0].split("=");
			String[] reasonsplit = temp[1].split("=");
			OutPlayerRequestDTO.OutPlayerInsertDTO outPlayerInsertDTO = new OutPlayerRequestDTO.OutPlayerInsertDTO();
			outPlayerInsertDTO.setPlayerId(Integer.parseInt(playerIdsplit[1]));
			outPlayerInsertDTO.setReason(reasonsplit[1]);
			int result = outPlayerService.insertOutPlayer(outPlayerInsertDTO.getPlayerId(), outPlayerInsertDTO.getReason());
			if(result == 1){
				System.out.println("성공");
			}
		}
	}

}
