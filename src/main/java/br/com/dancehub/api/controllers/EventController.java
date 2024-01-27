package br.com.dancehub.api.controllers;

import br.com.dancehub.api.event.CreateEventRequest;
import br.com.dancehub.api.event.EventAPI;
import br.com.dancehub.api.usecases.events.CreateEventUseCase;
import br.com.dancehub.api.usecases.events.UploadRuleFileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController implements EventAPI {

    private final CreateEventUseCase createEventUseCase;
    private final UploadRuleFileUseCase uploadRuleFileUseCase;
    // POO na veia

    @Override
    public ResponseEntity<?> createEvent(CreateEventRequest request) {
        final UUID uuid = this.createEventUseCase.execute(request);
        return ResponseEntity.created(URI.create("/v1/events/" + uuid.toString())).build();
    }

    @Override
    public ResponseEntity<?> uploadRules(MultipartFile multipartFile, String id) {
        if (this.uploadRuleFileUseCase.execute(multipartFile, id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().build();
    }
}
