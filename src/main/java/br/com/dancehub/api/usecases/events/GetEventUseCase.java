package br.com.dancehub.api.usecases.events;

import br.com.dancehub.api.event.EventEntity;
import br.com.dancehub.api.event.EventRepository;
import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.utils.UUIDUtils;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetEventUseCase {
    private final EventRepository eventRepository;

    public EventEntity execute(String id){
        return eventRepository.findById(UUIDUtils.getFromString(id)).orElseThrow(() -> new NotFoundEntityException(EventEntity.class, id));
    }
}
