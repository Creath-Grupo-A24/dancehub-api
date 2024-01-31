package br.com.dancehub.api.shared.exceptions;

import br.com.dancehub.api.contexts.user.role.Role;

import java.util.List;

public class PermissionException extends RuntimeException{
    public PermissionException(List<Role> roles) {
        super(String.format("Não foi possível realizar requisição com os cargos: %s", roles));
    }
}
