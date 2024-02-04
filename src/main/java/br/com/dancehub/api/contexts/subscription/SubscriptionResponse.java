package br.com.dancehub.api.contexts.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record SubscriptionResponse(
        @JsonProperty("id")
        String id,
        @JsonProperty("name")
        String name,
        @JsonProperty("description")
        String description,
        @JsonProperty("time")
        LocalDateTime time,
        @JsonProperty("category_id")
        String category,
        @JsonProperty("event_id")
        String event_id,
        @JsonProperty("staff")
        List<String> staff_ids
) {

}
