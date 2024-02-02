package br.com.dancehub.api.usecases.users;

import br.com.dancehub.api.config.JwtService;
import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.UserRepository;
import br.com.dancehub.api.shared.exceptions.NotFoundEntityException;
import br.com.dancehub.api.shared.utils.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetUserByTokenUseCase {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public User execute(final String tokenJwt) {
        final String id = this.jwtService.extractUuid(tokenJwt);
        return this.userRepository.findById(UUIDUtils.getFromString(id)).orElseThrow(() -> new NotFoundEntityException(User.class, id));
    }
}
