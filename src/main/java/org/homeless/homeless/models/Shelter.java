package org.homeless.homeless.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "shelters")
public class Shelter  extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String address;
    private Integer availableBeds;
}