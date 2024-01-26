package br.com.dancehub.api.validation.exceptions;

import br.com.dancehub.api.validation.FieldError;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private final List<FieldError> errors;

    public ValidationException(List<FieldError> errors) {
        super("Não foi possível validar os dados enviados.");
        this.errors = errors;
    }

}
