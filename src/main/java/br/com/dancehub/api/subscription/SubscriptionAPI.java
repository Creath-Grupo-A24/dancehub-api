package br.com.dancehub.api.subscription;

import br.com.dancehub.api.event.EventResponse;
import br.com.dancehub.api.usecases.subscription.create.CreateSubscriptionRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/subscription/")
public interface SubscriptionAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER') or hasRole('DANCER')")
    ResponseEntity<?> createSubscription(@RequestBody CreateSubscriptionRequest request);

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<EventResponse> getEvent(@PathVariable String id);
}
