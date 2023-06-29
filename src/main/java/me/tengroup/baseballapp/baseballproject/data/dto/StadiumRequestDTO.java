package me.tengroup.baseballapp.baseballproject.data.dto;

import lombok.*;
import me.tengroup.baseballapp.baseballproject.model.stadium.Stadium;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StadiumRequestDTO {
    private String name;

    public Stadium toEntity() {
        return Stadium.builder().name(name).build();
    }
}
