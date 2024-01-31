package br.com.dancehub.api.exceptions;

public class MessageException extends NoStacktraceException{
    public MessageException(String message) {
        super(message);
    }
}
