package br.com.dancehub.api.contexts.user.role;

public record RoleResponse(
        Integer id,
        String type,
        String description
) {
}
