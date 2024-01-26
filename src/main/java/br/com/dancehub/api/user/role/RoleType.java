package br.com.dancehub.api.user.role;

import lombok.Getter;

@Getter
public enum RoleType {
    ROLE_DANCER("Dançarino"),
    ROLE_TEACHER("Coreógrafo"),
    ROLE_MANAGER("Diretor da companhia"),
    ROLE_ADMIN("Funcionário da DanceHub");

    private final String description;

    RoleType(String description) {
        this.description = description;
    }

}
