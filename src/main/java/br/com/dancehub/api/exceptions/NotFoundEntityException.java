package br.com.dancehub.api.exceptions;

public class NotFoundEntityException extends RuntimeException{

    public NotFoundEntityException(Class<?> clazz, String id) {
        super(String.format("Não foi possível encontrar %s com id %s", clazz.getSimpleName(), id));
    }
}
