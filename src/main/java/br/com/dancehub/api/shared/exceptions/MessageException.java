package br.com.dancehub.api.shared.exceptions;

public class MessageException extends NoStacktraceException{
    public MessageException(String message) {
        super(message);
    }
}
