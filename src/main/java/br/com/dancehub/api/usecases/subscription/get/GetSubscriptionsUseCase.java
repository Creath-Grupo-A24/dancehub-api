package br.com.dancehub.api.usecases.subscription.get;

import br.com.dancehub.api.contexts.event.EventEntity;
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

import java.util.List;
import java.util.Optional;

import static br.com.dancehub.api.shared.utils.SpecificationUtils.equalBoolean;
import static br.com.dancehub.api.shared.utils.SpecificationUtils.like;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetSubscriptionsUseCase {
    private final SubscriptionRepository subscriptionRepository;

    public Pagination<SubscriptionEntity> execute(SearchQuery searchQuery) {
        final var pageConfig = PageRequest.of(
                searchQuery.page(),
                searchQuery.perPage(),
                Sort.by(Sort.Direction.fromString(searchQuery.direction()), searchQuery.sort())
        );

        final var filters = Optional.ofNullable(searchQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.subscriptionRepository.findAll(Specification.where(filters), pageConfig);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.toList()
        );
    }


    private Specification<SubscriptionEntity> assembleSpecification(final String str) {
        final Specification<SubscriptionEntity> nome = like("name", str);
        final Specification<SubscriptionEntity> local = like("place", str);
        return nome.or(local).and(equalBoolean("finished", false));
    }
}
