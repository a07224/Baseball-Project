package dto;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutPlayerRequestDTO {
    private int playerId;
    private String reason;
}
