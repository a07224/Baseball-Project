package me.tengroup.baseballapp.baseballproject.data.dto;

import lombok.*;
import me.tengroup.baseballapp.baseballproject.model.stadium.Stadium;
import me.tengroup.baseballapp.baseballproject.model.team.Team;


public class TeamRequestDTO {
    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeamInsertReqDTO{
        private int stadiumId;
        private String teamName;

        public Team toEntity() {
            return Team.builder()
                    .teamName(teamName)
                    .stadiumId(Stadium.builder().stadiumId(stadiumId).build().getStadiumId())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeamSelectReqDTO{
        private int id;
        private String stadiumName;
        private String teamName;
    }

}
