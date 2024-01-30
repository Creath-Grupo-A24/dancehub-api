package br.com.dancehub.api.controllers;

import br.com.dancehub.api.subscription.SubscriptionAPI;
import br.com.dancehub.api.usecases.subscription.create.CreateSubscriptionRequest;
import br.com.dancehub.api.usecases.subscription.create.CreateSubscriptionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubscriptionController implements SubscriptionAPI {
    private final CreateSubscriptionUseCase createsubscriptionUseCase;
    @Override
    public ResponseEntity<?> createSubscription(CreateSubscriptionRequest request) {
        final UUID uuid = this.createsubscriptionUseCase.execute(request);
        return ResponseEntity.created(URI.create("/v1/subscription/" + uuid.toString())).build();
    }
}
