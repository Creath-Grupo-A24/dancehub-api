package br.com.dancehub.api.usecases.event;

import br.com.dancehub.api.event.EventEntity;
import br.com.dancehub.api.event.EventRepository;
import br.com.dancehub.api.shared.Pagination;
import br.com.dancehub.api.shared.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.dancehub.api.utils.SpecificationUtils.*;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetEventsUseCase {

    private final EventRepository eventRepository;

    public Pagination<EventEntity> execute(SearchQuery searchQuery) {
        final var pageConfig = PageRequest.of(
                searchQuery.page(),
                searchQuery.perPage(),
                Sort.by(Sort.Direction.fromString(searchQuery.direction()), searchQuery.sort())
        );

        final var filters = Optional.ofNullable(searchQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.eventRepository.findAll(Specification.where(filters), pageConfig);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.toList()
        );
    }

    private Specification<EventEntity> assembleSpecification(final String str) {
        final Specification<EventEntity> nome = like("name", str);
        final Specification<EventEntity> local = like("place", str);
        return nome.or(local).and(equalBoolean("finished", false));
    }
}
