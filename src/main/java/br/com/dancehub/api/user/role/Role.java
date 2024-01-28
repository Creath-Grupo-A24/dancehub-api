package br.com.dancehub.api.user.role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Table(name = "creath_roles")
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, unique = true)
    private RoleType type;

    @Override
    public String getAuthority() {
        return this.type.name();
    }
}
