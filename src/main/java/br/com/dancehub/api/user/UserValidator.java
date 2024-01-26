package br.com.dancehub.api.user;

import br.com.dancehub.api.utils.ValidationUtils;
import br.com.dancehub.api.validation.ValidationErrorMessage;
import br.com.dancehub.api.validation.Validator;

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
        if (object.getEmail() == null || object.getEmail().isBlank())
            addError("email", ValidationErrorMessage.NOT_NULLABLE);
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
