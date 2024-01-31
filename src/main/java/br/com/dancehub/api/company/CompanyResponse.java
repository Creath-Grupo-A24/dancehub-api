package br.com.dancehub.api.company;

import br.com.dancehub.api.user.models.UserResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CompanyResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("name")
        String name,
        @JsonProperty("owner")
        UserResponse owner
) {
}
