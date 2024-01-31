package br.com.dancehub.api.usecases.company;

import br.com.dancehub.api.company.Company;
import br.com.dancehub.api.company.CompanyRepository;
import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetCompanyUseCase {

    private final CompanyRepository companyRepository;

    public Company execute(String id) {
        return companyRepository.findById(UUIDUtils.getFromString(id)).orElseThrow(() -> new NotFoundEntityException(Company.class, id));
    }
}
