package br.com.dancehub.api.contexts.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignInRequest(
        @JsonProperty("username")
        String username,
        @JsonProperty("password")
        String password
) {
}
