package br.com.dancehub.api.usecases.users;

import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.UserRepository;
import br.com.dancehub.api.shared.exceptions.NotFoundEntityException;
import br.com.dancehub.api.shared.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserByUsernameUseCase {

    private final UserRepository userRepository;

    public User execute(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(() -> new NotFoundEntityException(User.class, username));
    }

}
