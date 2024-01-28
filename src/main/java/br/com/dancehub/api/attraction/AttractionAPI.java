package br.com.dancehub.api.attraction;

import br.com.dancehub.api.usecases.attraction.create.CreateAttractionRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/attractions/")
public interface AttractionAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createAttraction(@RequestBody CreateAttractionRequest request);
}
