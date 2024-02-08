package br.com.dancehub.api.controllers;

import br.com.dancehub.api.contexts.user.AuthAPI;
import br.com.dancehub.api.contexts.user.UserApiPresenter;
import br.com.dancehub.api.contexts.user.models.AuthResponse;
import br.com.dancehub.api.contexts.user.models.SignInRequest;
import br.com.dancehub.api.contexts.user.models.SignUpRequest;
import br.com.dancehub.api.contexts.user.models.UserResponse;
import br.com.dancehub.api.contexts.user.role.RoleApiPresenter;
import br.com.dancehub.api.contexts.user.role.RoleResponse;
import br.com.dancehub.api.usecases.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController implements AuthAPI {

    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;
    private final GetUserByIDUseCase getUserByIDUseCase;
    private final GetUserByUsernameUseCase getUserByUsernameUseCase;
    private final GetRolesUseCase getRolesUseCase;

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
        return ResponseEntity.ok(UserApiPresenter.present(this.getUserByIDUseCase.execute(id)));
    }

    @Override
    public ResponseEntity<UserResponse> getUserByToken() {
        final String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(UserApiPresenter.present(this.getUserByUsernameUseCase.execute(username)));
    }

    @Override
    public ResponseEntity<List<RoleResponse>> getRoles() {
        return ResponseEntity.ok(this.getRolesUseCase.execute().stream().map(RoleApiPresenter::present).toList());
    }
}
