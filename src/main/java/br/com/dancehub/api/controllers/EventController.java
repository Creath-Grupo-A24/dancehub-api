package br.com.dancehub.api.controllers;

import br.com.dancehub.api.event.CreateEventRequest;
import br.com.dancehub.api.event.EventAPI;
import br.com.dancehub.api.usecases.events.CreateEventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController implements EventAPI {

    private final CreateEventUseCase createEventUseCase;

    @Override
    public ResponseEntity<?> createEvent(CreateEventRequest request) {
        final UUID uuid = this.createEventUseCase.execute(request);
        return ResponseEntity.created(URI.create("/v1/events/" + uuid.toString())).build();
    }
}
