package br.com.dancehub.api.validation;

public record FieldError(
        String field,
        ValidationErrorMessage message
) {
}
