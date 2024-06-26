package br.com.dancehub.api.contexts.subscription;

import br.com.dancehub.api.usecases.subscription.get.SubscriptionResponse;

public interface SubscriptionApiPresenter {

    static SubscriptionResponse present(SubscriptionEntity entity) {
        return new SubscriptionResponse(
                entity.getId().toString(),
                entity.getName(),
                entity.getDescription(),
                entity.getTime(),
                entity.getCategory().getType().name(),
                entity.getEvent().getId().toString(),
                entity.getStaff().stream().map(s -> s.getId().toString()).toList()
        );
    }
}
