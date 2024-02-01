package br.com.dancehub.api.usecases.subscription.get;

import br.com.dancehub.api.contexts.subscription.SubscriptionEntity;
import br.com.dancehub.api.contexts.subscription.SubscriptionRepository;
import br.com.dancehub.api.contexts.subscription.SubscriptionResponse;
import br.com.dancehub.api.shared.exceptions.NotFoundEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetSubscriptionUseCase {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionEntity execute(String id) {
        return subscriptionRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundEntityException(SubscriptionEntity.class, id.toString()));
    }
}
