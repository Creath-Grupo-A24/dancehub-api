package br.com.dancehub.api.contexts.user;

import br.com.dancehub.api.shared.utils.ValidationUtils;
import br.com.dancehub.api.shared.validation.ValidationErrorMessage;
import br.com.dancehub.api.shared.validation.Validator;

public class UserValidator extends Validator<User> {

    public UserValidator(final User object) {
        super(object);
    }

    @Override
    public UserValidator validate() {
        if (object == null) {
            addError("user", ValidationErrorMessage.INVALID);
            return this;
        }
        if (object.getUsername() == null || object.getUsername().isBlank())
            addError("username", ValidationErrorMessage.NOT_NULLABLE);
        if (object.getUsername() != null && (object.getUsername().length() > 16 || object.getUsername().contains(" ")))
            addError("username", ValidationErrorMessage.NOT_VALID_USERNAME);
        if (object.getEmail() == null || object.getEmail().isBlank())
            addError("email", ValidationErrorMessage.NOT_NULLABLE);
        if (object.getEmail() != null && !ValidationUtils.isValidEmail(object.getEmail()))
            addError("email", ValidationErrorMessage.NOT_VALID_EMAIL);
        if (object.getName() == null || object.getName().isBlank())
            addError("name", ValidationErrorMessage.NOT_NULLABLE);
        if (object.getRoles() == null || object.getRoles().isEmpty())
            addError("roles", ValidationErrorMessage.NOT_EMPTY);
        if (object.getCpf() == null || object.getCpf().isBlank())
            addError("cpf", ValidationErrorMessage.NOT_NULLABLE);
        else if (!ValidationUtils.isCPF(object.getCpf()))
            addError("cpf", ValidationErrorMessage.NOT_VALID_CPF);
        if (object.getPhone() != null && !ValidationUtils.isValidPhoneNumber(object.getPhone()))
            addError("phone", ValidationErrorMessage.NOT_VALID_PHONE_NUMBER);
        if (object.getBirthDate() == null)
            addError("birthDate", ValidationErrorMessage.NOT_NULLABLE);
        if (object.getPassword() == null || object.getPassword().isBlank())
            addError("password", ValidationErrorMessage.NOT_NULLABLE);

        return this;
    }
}
