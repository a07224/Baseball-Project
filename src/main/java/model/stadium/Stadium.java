package model.stadium;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import java.sql.Timestamp;

@ToString
@Builder
@Getter
public class Stadium {
    private int stadiumId;
    private String name;
    private Timestamp stadiumCreatedAt;

    @Builder
    public Stadium(int stadiumId, String name, Timestamp stadiumCreatedAt) {
        this.stadiumId = stadiumId;
        this.name = name;
        this.stadiumCreatedAt = stadiumCreatedAt;
    }
}
