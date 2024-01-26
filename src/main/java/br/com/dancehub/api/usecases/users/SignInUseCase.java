package br.com.dancehub.api.usecases.users;

import br.com.dancehub.api.config.JwtService;
import br.com.dancehub.api.user.User;
import br.com.dancehub.api.user.UserApiPresenter;
import br.com.dancehub.api.user.UserRepository;
import br.com.dancehub.api.user.models.AuthResponse;
import br.com.dancehub.api.user.models.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignInUseCase {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse execute(final SignInRequest request) {
        final String username = request.username();
        final String password = request.password();

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final Optional<User> user = this.userRepository.findByUsername(username);

        return user
                .map(entity -> new AuthResponse(this.jwtService.generateToken(entity), UserApiPresenter.present(entity)))
                .orElse(new AuthResponse( null, null));
    }
}
