package br.com.dancehub.api.user;

import br.com.dancehub.api.user.role.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creath_users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "creath_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Column(name = "password_date")
    private Date passwordDate;
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Builder
    public User(UUID id, List<Role> roles, String email, String cpf, String name, String phone, Date birthDate, Date passwordDate, String username, String password) {
        this.id = id;
        this.roles = roles;
        this.email = email;
        this.cpf = handleDocument(cpf);
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.passwordDate = passwordDate;
        this.username = username;
        this.password = password;
    }

    public static User newUser(String email, String cpf, String name, Role role, String phone, Date birthDate, String username, String password) {
        return User.builder()
                .email(email)
                .cpf(cpf)
                .name(name)
                .roles(List.of(role))
                .phone(phone)
                .birthDate(birthDate)
                .username(username)
                .password(password)
                .build();
    }

    private static String handleDocument(String document) {
        return document != null ? document.replaceAll("[^0-9]", "") : null;
    }

    public void changePassword(String password) {
        this.password = password;
        this.passwordDate = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
