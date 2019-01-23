package org.homeless.homeless.resources;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;
import org.homeless.homeless.models.Shelter;

@Getter
@Setter
public class ShelterResource {

    @JsonUnwrapped
    private Shelter shelter;

}
