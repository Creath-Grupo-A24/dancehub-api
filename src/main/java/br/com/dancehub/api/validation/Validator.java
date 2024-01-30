package br.com.dancehub.api.validation;

import br.com.dancehub.api.validation.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Validator<T> {

    protected final List<FieldError> errors = new ArrayList<>();
    protected final T object;

    public Validator(T object) {
        this.object = object;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public abstract Validator<T> validate();

    public Validator<T> addError(String field, ValidationErrorMessage message) {
        this.errors.add(new FieldError(field, message));
        return this;
    }

    public Validator<T> uniqueness(Supplier<Boolean> exists, String field) {
        if (exists.get())
            addError(field, ValidationErrorMessage.NOT_UNIQUE);
        return this;
    }

    public Validator<T> throwPossibleErrors() throws ValidationException {
        if (!errors.isEmpty())
            throw new ValidationException(this.errors);
        return this;
    }


}
