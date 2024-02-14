package br.com.dancehub.api.usecases.users;

import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.UserRepository;
import br.com.dancehub.api.shared.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUsersByCompanyUseCase {
    private final UserRepository userRepository;

    public List<User> execute(String companyId) {
        return this.userRepository.findAllByCompanyId(UUIDUtils.getFromString(companyId));
    }
}
