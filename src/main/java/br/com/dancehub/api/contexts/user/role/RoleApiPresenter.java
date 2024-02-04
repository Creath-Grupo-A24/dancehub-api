package br.com.dancehub.api.contexts.user.role;


public interface RoleApiPresenter {

    static RoleResponse present(Role entity) {
        return new RoleResponse(
                entity.getId(),
                entity.getType().name(),
                entity.getType().getDescription()
        );
    }
}
