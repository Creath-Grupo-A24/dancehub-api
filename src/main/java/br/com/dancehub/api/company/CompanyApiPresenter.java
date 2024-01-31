package br.com.dancehub.api.company;

import br.com.dancehub.api.user.UserApiPresenter;

public interface CompanyApiPresenter {

    static CompanyResponse present(Company company) {
        return new CompanyResponse(
                company.getId().toString(),
                company.getName(),
                UserApiPresenter.present(company.getOwner())
        );
    }
}
