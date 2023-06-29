package me.tengroup.baseballapp.baseballproject.data.dto;

import lombok.Data;

@Data
public class PlayerDTO {

    private String playerName;
    private String position;
    private int teamId;

    public PlayerRequestDTO toEntity() {
        return PlayerRequestDTO.builder().name(playerName)
                .position(position)
                .teamId(Team.builder().id(teamId).build())
                .build();
    }
}
