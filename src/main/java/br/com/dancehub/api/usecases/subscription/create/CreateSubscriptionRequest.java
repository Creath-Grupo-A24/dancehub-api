package br.com.dancehub.api.usecases.subscription.create;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record CreateSubscriptionRequest(
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
        @JsonProperty("dancers")
        List<String> dancers_id
) {
}
