package br.com.dancehub.api.controllers;

import br.com.dancehub.api.attraction.AttractionAPI;
import br.com.dancehub.api.usecases.attraction.create.CreateAttractionRequest;
import br.com.dancehub.api.usecases.attraction.create.CreateAttractionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AttractionController implements AttractionAPI {
    private final CreateAttractionUseCase createAttractionUseCase;
    @Override
    public ResponseEntity<?> createAttraction(CreateAttractionRequest request) {
        final UUID uuid = this.createAttractionUseCase.execute(request);
        return ResponseEntity.created(URI.create("/v1/attractions/" + uuid.toString())).build();
    }
}
