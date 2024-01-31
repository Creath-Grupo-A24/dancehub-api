package br.com.dancehub.api.shared.validation;

public record FieldError(
        String field,
        ValidationErrorMessage message
) {
}
