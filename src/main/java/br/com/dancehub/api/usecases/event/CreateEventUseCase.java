package br.com.dancehub.api.usecases.event;

import br.com.dancehub.api.contexts.event.*;
import br.com.dancehub.api.contexts.event.category.Category;
import br.com.dancehub.api.contexts.event.category.CategoryRepository;
import br.com.dancehub.api.contexts.event.models.CreateEventRequest;
import br.com.dancehub.api.shared.exceptions.NotFoundEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateEventUseCase {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;

    public UUID execute(CreateEventRequest req) {
        final List<Category> categories = req.categories()
                .stream()
                .map(s -> this.categoryRepository.findById(s)
                        .orElseThrow(() -> new NotFoundEntityException(Category.class, s.toString()))).toList();
        final String place = req.place();
        final String description = req.description();
        final String name = req.name();
        final LocalDateTime time = req.time();

        final EventEntity entity = EventEntity.builder()
                .finished(false)
                .categories(categories)
                .place(place)
                .description(description)
                .name(name)
                .time(time).build();

        return eventRepository.save(entity).getId();
    }


}
