package me.tengroup.baseballapp.baseballproject.data.dto;

import lombok.*;
import me.tengroup.baseballapp.baseballproject.model.player.Player;
import me.tengroup.baseballapp.baseballproject.model.team.Team;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDTO {
    private int teamId;
    private String name;
    private String position;

    public Player toEntity() {
        Timestamp playerName;
        return Player.builder()
                .name(name)
                .position(position)
                .teamId(Team.builder().id(teamId).build())
                .build();
    }
}
