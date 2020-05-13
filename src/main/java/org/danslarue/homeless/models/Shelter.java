package org.danslarue.homeless.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.danslarue.homeless.dto.ShelterDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "shelter", schema = "public")
public class Shelter extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;
    private String address;
    private String news;
    private Integer availableBeds;

    public Shelter(ShelterDTO shelterDTO) {
        //TODO: business logic tests
        this.address = shelterDTO.getAddress();
        this.availableBeds = shelterDTO.getAvailableBeds();
        this.news = shelterDTO.getNews();
    }
}
