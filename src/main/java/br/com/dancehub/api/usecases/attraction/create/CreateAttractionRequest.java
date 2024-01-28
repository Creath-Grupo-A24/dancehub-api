package br.com.dancehub.api.usecases.attraction.create;

import br.com.dancehub.api.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record CreateAttractionRequest(
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("time")
        LocalDateTime time,
        @JsonProperty("category")
        String category,
        @JsonProperty("event_id")
        String event_id,
        @JsonProperty("director")
        String director_id,
        @JsonProperty("choreographer")
        String choreographer_id,
        @JsonProperty("dancers")
        List<String> dancers_id
) {
}
