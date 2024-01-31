package br.com.dancehub.api.contexts.company.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCompanyRequest(
        @JsonProperty("owner_id")
        String ownerId,
        @JsonProperty("name")
        String name
) {
}
