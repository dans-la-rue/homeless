package com.dakar.dakar.resources;

import com.dakar.dakar.models.Shelter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
public class ShelterResource extends ResourceSupport {

    @JsonUnwrapped
    private Shelter shelter;

}
