package br.com.dancehub.api.controllers;

import br.com.dancehub.api.company.CompanyAPI;
import br.com.dancehub.api.company.models.CreateCompanyRequest;
import br.com.dancehub.api.event.CreateEventRequest;
import br.com.dancehub.api.usecases.company.CreateCompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyController implements CompanyAPI {

    private final CreateCompanyUseCase createCompanyUseCase;

    public ResponseEntity<?> createCompany(CreateCompanyRequest request) {
        final String uuid = this.createCompanyUseCase.execute(request);
        return ResponseEntity.created(URI.create("/v1/companies/" + uuid)).build();
    }
}
