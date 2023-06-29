package me.tengroup.baseballapp.baseballproject.model.outPlayer;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class OutPlayer {
    private int id;
    private int playerId;
    private String reason;
    private Timestamp outPlayerCreatedAt;

    @Builder
    public OutPlayer(int id, int playerId, String reason, Timestamp outPlayerCreatedAt) {
        this.id = id;
        this.playerId = playerId;
        this.reason = reason;
        this.outPlayerCreatedAt = outPlayerCreatedAt;
    }
}
