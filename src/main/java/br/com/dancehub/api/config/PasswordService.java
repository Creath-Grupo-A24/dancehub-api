package br.com.dancehub.api.config;

public interface PasswordService {

    boolean validatePassword(String pass);

    String encode(String pass);
}
