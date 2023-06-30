package me.tengroup.baseballapp.baseballproject.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tengroup.baseballapp.baseballproject.model.team.Team;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlayerDTO {

    private String playerName;
    private String position;
    private int teamId;

    public PlayerRequestDTO toEntity() {
        return PlayerRequestDTO.builder().name(playerName)
                .position(position)
                .teamId(Team.builder().id(teamId).build()).build();
    }
}
