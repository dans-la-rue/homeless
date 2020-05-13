package org.danslarue.homeless.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelterDTO {
    private Integer id;
    private String address;
    private String news;
    private Integer availableBeds;
}
