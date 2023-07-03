package model.team;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import model.stadium.Stadium;

import java.sql.Timestamp;

@Getter
@ToString
public class Team {
    private int id;
    private int stadiumId;
    private String teamName;
    private Timestamp teamCreatedAt;
    @Builder
    public Team(int id, int stadiumId, String teamName, Timestamp teamCreatedAt) {
        this.id = id;
        this.stadiumId = stadiumId;
        this.teamName = teamName;
        this.teamCreatedAt = teamCreatedAt;
    }
}