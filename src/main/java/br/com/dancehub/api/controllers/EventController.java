package br.com.dancehub.api.controllers;

import br.com.dancehub.api.event.*;
import br.com.dancehub.api.shared.Pagination;
import br.com.dancehub.api.shared.SearchQuery;
import br.com.dancehub.api.usecases.events.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController implements EventAPI {

    private final GetEventUseCase getEventUseCase;
    private final DownloadRuleFileUseCase downloadRuleFileUseCase;
    private final CreateEventUseCase createEventUseCase;
    private final UploadRuleFileUseCase uploadRuleFileUseCase;
    private final GetEventsUseCase getEventsUseCase;

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

    @Override
    public ResponseEntity<byte[]> downloadRules(String id) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(downloadRuleFileUseCase.execute(id));
    }

    @Override
    public ResponseEntity<EventResponse> getEvent(String id) {
        return ResponseEntity.ok(EventApiPresenter.present(getEventUseCase.execute(id)));
    }

    @Override
    public ResponseEntity<Pagination<EventResponse>> getEvents(Integer page, Integer perPage, String terms, String sort, String direction) {
        final SearchQuery searchQuery = new SearchQuery(page, perPage, terms, sort, direction);
        return ResponseEntity.ok(this.getEventsUseCase.execute(searchQuery).map(EventApiPresenter::present));
    }
}
