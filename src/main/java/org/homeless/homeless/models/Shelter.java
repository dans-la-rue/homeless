package org.homeless.homeless.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shelter {

    private String id;
    private String price;
    @NonNull
    private String destination;
    private String owner;

}