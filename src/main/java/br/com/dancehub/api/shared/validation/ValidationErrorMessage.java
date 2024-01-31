package br.com.dancehub.api.shared.validation;

import lombok.Getter;

@Getter
public enum ValidationErrorMessage {

    INVALID(1, "O objeto informado para validação é nulo."),
    NOT_NULLABLE(2, "O campo '{field}' não pode ser nulo ou vazio."),
    NOT_ID(3, "O campo '{field}' não é um número de ID válido."),
    NOT_VALID_NUMBER(4, "O campo '{field}' não é um número válido."),
    NOT_VALID_PHONE_NUMBER(5, "O campo '{field}' não é um número de telefone válido."),
    NOT_NEGATIVE_NUMBER(6, "O campo '{field}' não pode ser um número nulo ou negativo."),
    NOT_VALID_DATE(7, "O campo '{field}' não possui uma data válida (dd/MM/yyyy)."),
    NOT_VALID_CPF(8, "O campo '{field}' não possui um CPF válido."),
    NOT_VALID_TIME(9, "O campo '{field}' não possui um horário válido (HH:mm)."),
    NOT_REPEATABLE(10, "Os campos '{field}' estão idênticos."),
    NOT_EMPTY(11, "O campo '{field}' não pode estar vazio."),
    NOT_UNIQUE(12, "O valor do campo '{field}' já existe em nossa base de dados."),
    NOT_VALID_USERNAME(13, "O campo '{field}' não é um username válido"),
    NOT_VALID_EMAIL(14, "O campo '{field} não é um email válido");



    private final Integer code;
    private final String text;

    ValidationErrorMessage(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getText(String field) {
        return text.replace("{field}", field);
    }
}
