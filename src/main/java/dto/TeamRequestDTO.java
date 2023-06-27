package dto;

import lombok.*;

import java.sql.Timestamp;


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
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TeamSelectReqDTO{
        private int id;
        private String stadiumName;
        private String teamName;
    }


}
