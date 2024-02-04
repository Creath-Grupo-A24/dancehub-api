package br.com.dancehub.api.usecases.subscription.get;

import br.com.dancehub.api.contexts.subscription.SubscriptionEntity;
import br.com.dancehub.api.contexts.subscription.SubscriptionRepository;
import br.com.dancehub.api.shared.Pagination;
import br.com.dancehub.api.shared.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetSubscriptionByEventIdUseCase {
    private final SubscriptionRepository subscriptionRepository;

    public Pagination<SubscriptionEntity> execute(String eventId, SearchQuery searchQuery) {
        final var pageConfig = PageRequest.of(
                searchQuery.page(),
                searchQuery.perPage(),
                Sort.by(Sort.Direction.fromString(searchQuery.direction()), searchQuery.sort())
        );

        final var pageResult = subscriptionRepository.findByEventId(UUID.fromString(eventId), pageConfig);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.toList());
    }
}
