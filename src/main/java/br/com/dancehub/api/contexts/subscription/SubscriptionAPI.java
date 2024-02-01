package br.com.dancehub.api.contexts.subscription;

import br.com.dancehub.api.contexts.event.EventResponse;
import br.com.dancehub.api.shared.Pagination;
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
    ResponseEntity<SubscriptionResponse> getSubscription(@PathVariable String id);

    @GetMapping(
            value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Pagination<SubscriptionResponse>> getSubscriptions(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") Integer perPage,
            @RequestParam(name = "terms", required = false) String terms,
            @RequestParam(name = "sort", required = false, defaultValue = "time") String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction
    );

    @GetMapping(
            value = "/event/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Pagination<SubscriptionResponse>> getSubscriptionByEventId(
            @PathVariable String id,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") Integer perPage,
            @RequestParam(name = "terms", required = false) String terms,
            @RequestParam(name = "sort", required = false, defaultValue = "time") String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction
    );
}
