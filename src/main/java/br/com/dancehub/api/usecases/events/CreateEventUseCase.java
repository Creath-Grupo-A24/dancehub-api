package br.com.dancehub.api.usecases.events;

import br.com.dancehub.api.event.*;
import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.utils.UUIDUtils;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateEventUseCase {

    // a gente precisa do objeto de dados iniciais
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public UUID execute(CreateEventRequest req) {
        final List<Category> categories = req.categories()
                .stream()
                .map(s -> this.categoryRepository.findById(Integer.parseInt(s))
                        .orElseThrow(() -> new NotFoundEntityException(Category.class, s))).toList();
        final String local = req.local();
        final String description = req.description();
        final String name = req.name();
        final LocalDateTime time = req.time();

        final EventEntity entity = EventEntity.builder()
                .categories(categories)
                .local(local)
                .description(description)
                .name(name)
                .time(time).build();

        return eventRepository.save(entity).getId();
    }


}
