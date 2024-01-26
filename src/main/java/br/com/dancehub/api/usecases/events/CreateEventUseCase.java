package br.com.dancehub.api.usecases.events;

import br.com.dancehub.api.event.CategoryType;
import br.com.dancehub.api.event.CreateEventRequest;
import br.com.dancehub.api.event.EventEntity;
import br.com.dancehub.api.event.EventRepository;
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

    public UUID execute(CreateEventRequest req){
        final List<CategoryType> categories = req.categories().stream().map(CategoryType::valueOf).toList();
        final String local = req.local();
        final String description = req.description();
        final String rules = req.rules();
        final String name = req.name();
        final LocalDateTime time = req.time();

        final EventEntity entity = EventEntity.builder()
                .categories(categories)
                .rules(rules)
                .local(local)
                .description(description)
                .name(name)
                .time(time).build();


        return eventRepository.save(entity).getId();
    }


}
