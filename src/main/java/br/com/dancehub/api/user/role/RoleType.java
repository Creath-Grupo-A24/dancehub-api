package br.com.dancehub.api.user.role;

import lombok.Getter;

@Getter
public enum RoleType {
    DANCER("Dançarino"),
    TEACHER("Coreógrafo"),
    MANAGER("Diretor da companhia"),
    ADMIN("Funcionário da DanceHub");

    private final String description;

    RoleType(String description) {
        this.description = description;
    }

}
