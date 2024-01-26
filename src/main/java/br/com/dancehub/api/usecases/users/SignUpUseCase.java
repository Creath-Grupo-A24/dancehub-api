package br.com.dancehub.api.usecases.users;

import br.com.dancehub.api.config.JwtService;
import br.com.dancehub.api.config.PasswordService;
import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.user.User;
import br.com.dancehub.api.user.UserApiPresenter;
import br.com.dancehub.api.user.UserRepository;
import br.com.dancehub.api.user.UserValidator;
import br.com.dancehub.api.user.models.AuthResponse;
import br.com.dancehub.api.user.models.SignUpRequest;
import br.com.dancehub.api.user.role.Role;
import br.com.dancehub.api.user.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SignUpUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    public AuthResponse execute(final SignUpRequest request) {
        final String username = request.username();
        final String email = request.email();
        final String password = request.password();
        final String name = request.name();
        final Integer roleId = request.roleId();
        final String cpf = request.cpf();
        final String phone = request.phone();
        final Date birthDate = request.birthDate();

        final Role role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundEntityException(Role.class, roleId.toString()));

        User user = User.newUser(email, cpf, name, role, phone, birthDate, username, this.passwordService.encode(password));


        final UserValidator validator = new UserValidator(user);
        validator.validate()
                .throwPossibleErrors()
                .uniqueness(() -> this.userRepository.existsByEmail(email), "email")
                .uniqueness(() -> this.userRepository.existsByUsername(username), "username")
                .uniqueness(() -> this.userRepository.existsByCpf(cpf), "cpf")
                .throwPossibleErrors();

        user = this.userRepository.save(user);

        return new AuthResponse(this.jwtService.generateToken(user), UserApiPresenter.present(user));
    }


}
