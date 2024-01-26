package br.com.dancehub.api.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(
        @JsonProperty("token")
        String token,
        @JsonProperty("user")
        UserResponse user
) {
}
