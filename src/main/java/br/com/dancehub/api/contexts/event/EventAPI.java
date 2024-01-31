package br.com.dancehub.api.contexts.event;

import br.com.dancehub.api.shared.Pagination;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/v1/events/")
public interface EventAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> createEvent(@RequestBody CreateEventRequest request);

    @PostMapping(
            value = "/rules/{id}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> uploadRules(@RequestParam("file") MultipartFile multipartFile, @PathVariable String id);

    @GetMapping(
            value = "/rules/{id}/download",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    ResponseEntity<byte[]> downloadRules(@PathVariable String id);

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<EventResponse> getEvent(@PathVariable String id);

    @GetMapping(
            value = "/list",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Pagination<EventResponse>> getEvents(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") Integer perPage,
            @RequestParam(name = "terms", required = false) String terms,
            @RequestParam(name = "sort", required = false, defaultValue = "time") String sort,
            @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction
    );



}
