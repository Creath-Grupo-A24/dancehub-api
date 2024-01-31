package br.com.dancehub.api.controllers;

import br.com.dancehub.api.company.CompanyAPI;
import br.com.dancehub.api.company.CompanyApiPresenter;
import br.com.dancehub.api.company.CompanyResponse;
import br.com.dancehub.api.company.models.CreateCompanyRequest;
import br.com.dancehub.api.usecases.company.CreateCompanyUseCase;
import br.com.dancehub.api.usecases.company.GetCompanyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyController implements CompanyAPI {

    private final GetCompanyUseCase getCompanyUseCase;
    private final CreateCompanyUseCase createCompanyUseCase;

    @Override
    public ResponseEntity<?> createCompany(CreateCompanyRequest request) {
        final String uuid = this.createCompanyUseCase.execute(request);
        return ResponseEntity.created(URI.create("/v1/companies/" + uuid)).build();
    }

    @Override
    public ResponseEntity<CompanyResponse> getCompany(String id) {
        return ResponseEntity.ok(CompanyApiPresenter.present(this.getCompanyUseCase.execute(id)));
    }
}
