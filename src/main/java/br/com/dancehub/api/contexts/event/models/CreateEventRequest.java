package br.com.dancehub.api.contexts.event.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record CreateEventRequest(
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("place")
        String place,
        @JsonProperty("time")
        LocalDateTime time,
        @JsonProperty("categories_ids")
        List<Integer> categories
) {

}
