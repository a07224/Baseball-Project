package me.tengroup.baseballapp.baseballproject.data.domain;


import lombok.*;

import java.sql.Timestamp;

public class OutPlayerRequestDTO {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OutPlayerInsertDTO{
        private int playerId;
        private String reason;
    }
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
