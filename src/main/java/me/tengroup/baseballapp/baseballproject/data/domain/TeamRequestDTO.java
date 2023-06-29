package me.tengroup.baseballapp.baseballproject.data.domain;

import lombok.*;


public class TeamRequestDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TeamInsertReqDTO{
        private int stadiumId;
        private String teamName;
    }
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
