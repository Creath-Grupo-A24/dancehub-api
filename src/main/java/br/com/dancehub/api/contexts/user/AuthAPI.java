package br.com.dancehub.api.contexts.user;

import br.com.dancehub.api.contexts.user.models.AuthResponse;
import br.com.dancehub.api.contexts.user.models.SignInRequest;
import br.com.dancehub.api.contexts.user.models.SignUpRequest;
import br.com.dancehub.api.contexts.user.models.UserResponse;
import br.com.dancehub.api.contexts.user.role.RoleResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/sign/")
public interface AuthAPI {

    @PostMapping(
            value = "/in",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthResponse> authSignIn(@RequestBody SignInRequest request);

    @PostMapping(
            value = "/up",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AuthResponse> authSignUp(@RequestBody SignUpRequest request);

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> getUser(@PathVariable String id);

    @GetMapping(
            value = "/token",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<UserResponse> getUserByToken();

    @GetMapping(
            value = "/roles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<List<RoleResponse>> getRoles();

}
