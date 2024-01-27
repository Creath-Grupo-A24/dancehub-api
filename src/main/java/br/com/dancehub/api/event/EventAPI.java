package br.com.dancehub.api.event;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/v1/events/")
public interface EventAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createEvent(@RequestBody CreateEventRequest request);

    @PostMapping(
            value = "/{id}/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<?> uploadRules(@RequestParam("file") MultipartFile multipartFile, @PathVariable String id);



}
