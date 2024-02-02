package br.com.dancehub.api.controllers;

import br.com.dancehub.api.contexts.user.AuthAPI;
import br.com.dancehub.api.contexts.user.UserApiPresenter;
import br.com.dancehub.api.contexts.user.models.AuthResponse;
import br.com.dancehub.api.contexts.user.models.SignInRequest;
import br.com.dancehub.api.contexts.user.models.SignUpRequest;
import br.com.dancehub.api.contexts.user.models.UserResponse;
import br.com.dancehub.api.usecases.users.GetUserByTokenUseCase;
import br.com.dancehub.api.usecases.users.GetUserUseCase;
import br.com.dancehub.api.usecases.users.SignInUseCase;
import br.com.dancehub.api.usecases.users.SignUpUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController implements AuthAPI {

    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetUserByTokenUseCase getUserByTokenUseCase;

    @Override
    public ResponseEntity<AuthResponse> authSignIn(SignInRequest request) {
        final AuthResponse response = this.signInUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AuthResponse> authSignUp(SignUpRequest request) {
        return ResponseEntity.ok(this.signUpUseCase.execute(request));
    }

    @Override
    public ResponseEntity<UserResponse> getUser(String id) {
        return ResponseEntity.ok(UserApiPresenter.present(this.getUserUseCase.execute(id)));
    }

    @Override
    public ResponseEntity<UserResponse> getUserByToken(String token) {
        return ResponseEntity.ok(UserApiPresenter.present(this.getUserByTokenUseCase.execute(token)));
    }
}
