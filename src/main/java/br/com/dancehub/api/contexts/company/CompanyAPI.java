package br.com.dancehub.api.contexts.company;

import br.com.dancehub.api.contexts.company.models.CreateCompanyRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/companies/")
public interface CompanyAPI {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('MANAGER')")
    ResponseEntity<?> createCompany(@RequestBody CreateCompanyRequest request);

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<CompanyResponse> getCompany(@PathVariable String id);
}
