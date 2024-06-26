package br.com.dancehub.api.usecases.event;

import br.com.dancehub.api.contexts.event.EventEntity;
import br.com.dancehub.api.contexts.event.EventRepository;
import br.com.dancehub.api.shared.exceptions.NotFoundEntityException;
import br.com.dancehub.api.shared.utils.UUIDUtils;
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
