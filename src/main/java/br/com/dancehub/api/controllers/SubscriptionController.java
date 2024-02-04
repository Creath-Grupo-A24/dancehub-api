package br.com.dancehub.api.controllers;

import br.com.dancehub.api.contexts.event.EventResponse;
import br.com.dancehub.api.contexts.subscription.SubscriptionAPI;
import br.com.dancehub.api.contexts.subscription.SubscriptionApiPresenter;
import br.com.dancehub.api.contexts.subscription.SubscriptionResponse;
import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.shared.Pagination;
import br.com.dancehub.api.shared.SearchQuery;
import br.com.dancehub.api.shared.exceptions.MessageException;
import br.com.dancehub.api.shared.exceptions.PermissionException;
import br.com.dancehub.api.usecases.subscription.create.CreateSubscriptionRequest;
import br.com.dancehub.api.usecases.subscription.create.CreateSubscriptionUseCase;
import br.com.dancehub.api.usecases.subscription.get.GetSubscriptionByEventIdUseCase;
import br.com.dancehub.api.usecases.subscription.get.GetSubscriptionUseCase;
import br.com.dancehub.api.usecases.subscription.get.GetSubscriptionsUseCase;
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
    private final GetSubscriptionUseCase getSubscriptionUseCase;
    private final GetSubscriptionsUseCase getSubscriptionsUseCase;
    private final GetSubscriptionByEventIdUseCase getSubscriptionByEventIdUseCase;

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
    public ResponseEntity<SubscriptionResponse> getSubscription(String id) {
        return ResponseEntity.ok(
                SubscriptionApiPresenter.present(this.getSubscriptionUseCase.execute(id))
        );
    }

    @Override
    public ResponseEntity<Pagination<SubscriptionResponse>> getSubscriptions(Integer page, Integer perPage, String terms, String sort, String direction) {
        final SearchQuery searchQuery = new SearchQuery(page, perPage, terms, sort, direction);
        return ResponseEntity.ok(
                this.getSubscriptionsUseCase.execute(searchQuery).map(SubscriptionApiPresenter::present)
        );
    }

    @Override
    public ResponseEntity<Pagination<SubscriptionResponse>> getSubscriptionByEventId(String eventId, Integer page, Integer perPage, String terms, String sort, String direction) {
        final SearchQuery searchQuery = new SearchQuery(page, perPage, terms, sort, direction);

        return ResponseEntity.ok(
                this.getSubscriptionByEventIdUseCase.execute(eventId, searchQuery)
                        .map(SubscriptionApiPresenter::present)
        );
    }

}
