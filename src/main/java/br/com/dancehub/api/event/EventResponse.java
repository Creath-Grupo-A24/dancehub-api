package br.com.dancehub.api.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record EventResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("place")
        String place,
        @JsonProperty("time")
        LocalDateTime time,
        @JsonProperty("categories")
        List<String> categories
) {
}
