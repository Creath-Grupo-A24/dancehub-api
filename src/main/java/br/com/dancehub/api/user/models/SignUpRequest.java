package br.com.dancehub.api.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record SignUpRequest(
        @JsonProperty("username")
        String username,
        @JsonProperty("email")
        String email,
        @JsonProperty("password")
        String password,
        @JsonProperty("name")
        String name,
        @JsonProperty("birth_date")
        Date birthDate,
        @JsonProperty("role_id")
        Integer roleId,
        @JsonProperty("cpf")
        String cpf,
        @JsonProperty("phone")
        String phone
) {
}
