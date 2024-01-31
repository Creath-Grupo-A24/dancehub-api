package br.com.dancehub.api.contexts.company;

import br.com.dancehub.api.contexts.user.UserApiPresenter;

public interface CompanyApiPresenter {

    static CompanyResponse present(Company company) {
        return new CompanyResponse(
                company.getId().toString(),
                company.getName(),
                UserApiPresenter.present(company.getOwner())
        );
    }
}
