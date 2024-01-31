package br.com.dancehub.api.company;

import br.com.dancehub.api.company.models.CreateCompanyRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/companies/")
public interface CompanyAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<?> createCompany(@RequestBody CreateCompanyRequest request);
}
