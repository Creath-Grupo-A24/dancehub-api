package br.com.dancehub.api.contexts.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public record UserResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("username")
        String username,
        @JsonProperty("email")
        String email,
        @JsonProperty("name")
        String name,
        @JsonProperty("roles")
        List<String> roles,
        @JsonProperty("cpf")
        String cpf,
        @JsonProperty("phone")
        String phone,
        @JsonProperty("birth_date")
        Date birthDate,
        @JsonProperty("company_id")
        String companyId
) {
}
