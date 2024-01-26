package br.com.dancehub.api.controllers;

import br.com.dancehub.api.usecases.users.SignInUseCase;
import br.com.dancehub.api.usecases.users.SignUpUseCase;
import br.com.dancehub.api.user.AuthAPI;
import br.com.dancehub.api.user.models.AuthResponse;
import br.com.dancehub.api.user.models.SignInRequest;
import br.com.dancehub.api.user.models.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController implements AuthAPI {

    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;

    @Override
    public ResponseEntity<AuthResponse> authSignIn(SignInRequest request) {
        final AuthResponse response = this.signInUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AuthResponse> authSignUp(SignUpRequest request) {
        return ResponseEntity.ok(this.signUpUseCase.execute(request));
    }
}
