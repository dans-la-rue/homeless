package org.homeless.homeless.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ShelterInput {

    private String destination;
    private String price;
    private String owner;

}
