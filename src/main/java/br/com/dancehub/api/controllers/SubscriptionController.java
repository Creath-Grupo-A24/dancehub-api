package br.com.dancehub.api.controllers;

import br.com.dancehub.api.event.EventResponse;
import br.com.dancehub.api.exceptions.MessageException;
import br.com.dancehub.api.exceptions.PermissionException;
import br.com.dancehub.api.subscription.SubscriptionAPI;
import br.com.dancehub.api.usecases.subscription.create.CreateSubscriptionRequest;
import br.com.dancehub.api.usecases.subscription.create.CreateSubscriptionUseCase;
import br.com.dancehub.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SubscriptionController implements SubscriptionAPI {
    private final CreateSubscriptionUseCase createsubscriptionUseCase;
    @Override
    public ResponseEntity<?> createSubscription(CreateSubscriptionRequest request) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final UUID uuid;
        final User user = (User) auth.getPrincipal();
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("MANAGER"))) {
            uuid = this.createsubscriptionUseCase.execute(request);
        } else if(auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("DANCER"))){
            if(user.getCompanyId() == null){
                uuid = this.createsubscriptionUseCase.execute(request);
            }else throw new MessageException("Você já tem associação com uma companhia. Peça para seu" +
                    "gerente te inscrever no evento!");
        }else throw new PermissionException(user.getRoles());

        return ResponseEntity.created(URI.create("/v1/subscription/" + uuid.toString())).build();
    }

    @Override
    public ResponseEntity<EventResponse> getEvent(String id) {
        return null;
    }
}
