package dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDTO {
    private int teamId;
    private String name;
    private String position;
}
