package br.com.dancehub.api.user;

import br.com.dancehub.api.user.models.AuthResponse;
import br.com.dancehub.api.user.models.SignInRequest;
import br.com.dancehub.api.user.models.SignUpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/auth/sign")
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

}
