package br.com.dancehub.api.contexts.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.format.DateTimeFormatter;
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
        String birthDate,
        @JsonProperty("role_id")
        Integer roleId,
        @JsonProperty("cpf")
        String cpf,
        @JsonProperty("phone")
        String phone
) {

    public Date getBirthDate() {
        return Date.from(java.time.LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")).atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
    }
}
