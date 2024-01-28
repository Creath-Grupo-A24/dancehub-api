package br.com.dancehub.api.usecases.users;

import br.com.dancehub.api.config.JwtService;
import br.com.dancehub.api.user.User;
import br.com.dancehub.api.user.UserApiPresenter;
import br.com.dancehub.api.user.models.AuthResponse;
import br.com.dancehub.api.user.models.SignInRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignInUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse execute(final SignInRequest request) {
        final String username = request.username();
        final String password = request.password();

        final Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        final User details = (User) authenticate.getDetails();
        return new AuthResponse(this.jwtService.generateToken(details), UserApiPresenter.present(details));
    }
}
