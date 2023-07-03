package me.tengroup.baseballapp.baseballproject.data.dto;


import lombok.*;

import java.sql.Timestamp;

public class OutPlayerRequestDTO {

    @Data
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutPlayerInsertDTO{
        private int playerId;
        private String reason;
    }

    @Data
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutPlayerSelectDTO{
        private int id;
        private String name;
        private String position;
        private String reason;
        private Timestamp outPlayerCreatedAt;
    }
}
