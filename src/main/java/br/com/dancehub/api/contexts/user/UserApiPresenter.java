package br.com.dancehub.api.contexts.user;

import br.com.dancehub.api.contexts.user.models.UserResponse;

public interface UserApiPresenter {

    static UserResponse present(final User user) {
        if (user == null) return null;
        return new UserResponse(
                user.getId().toString(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getRoles().stream().map(role -> role.getType().name()).toList(),
                user.getCpf(),
                user.getPhone(),
                user.getBirthDate(),
                user.getCompanyId() == null ? null : user.getCompanyId().toString()
        );

    }
}
