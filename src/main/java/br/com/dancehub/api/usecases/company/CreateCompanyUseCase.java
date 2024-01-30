package br.com.dancehub.api.usecases.company;

import br.com.dancehub.api.company.Company;
import br.com.dancehub.api.company.CompanyRepository;
import br.com.dancehub.api.company.models.CreateCompanyRequest;
import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.user.User;
import br.com.dancehub.api.user.UserRepository;
import br.com.dancehub.api.user.role.RoleType;
import br.com.dancehub.api.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public String execute(CreateCompanyRequest request){
        final User user = userRepository.findById(UUIDUtils.getFromString(request.ownerId())).orElseThrow(() -> new NotFoundEntityException(User.class, request.ownerId()));
        if (companyRepository.existsByName(request.name()))
            return null;
        if (user.getRoles().stream().noneMatch(role -> role.getType() == RoleType.MANAGER))
            return null;

        Company company = Company.builder()
                .owner(user)
                .name(request.name())
                .build();

        company = companyRepository.save(company);
        user.setCompanyId(company.getId());
        this.userRepository.save(user);
        return company.getId().toString();
    }
}
