package br.com.dancehub.api.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record CreateEventRequest(
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("local")
        String local,
        @JsonProperty("time")
        LocalDateTime time,
        @JsonProperty("rules")
        String rules,
        @JsonProperty("categories")
        List<String> categories
) {

}
