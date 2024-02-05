package br.com.dancehub.api.usecases.subscription.create;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record   CreateSubscriptionRequest(
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("time")
        LocalDateTime time,
        @JsonProperty("category_id")
        Integer categoryId,
        @JsonProperty("event_id")
        String event_id,
        @JsonProperty("staff")
        List<String> staff_id
) {
}
