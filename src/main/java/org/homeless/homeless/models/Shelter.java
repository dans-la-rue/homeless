package org.homeless.homeless.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shelter {

    @Id
    private String id;
    private String price;
    @NonNull
    private String destination;
    private String owner;

}