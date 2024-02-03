package br.com.dancehub.api.usecases.users;

import br.com.dancehub.api.contexts.user.role.Role;
import br.com.dancehub.api.contexts.user.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetRolesUseCase {

    private final RoleRepository roleRepository;

    public List<Role> execute() {
        return this.roleRepository.findAll();
    }
}
