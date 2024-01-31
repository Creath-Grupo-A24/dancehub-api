package br.com.dancehub.api.contexts.company;


import br.com.dancehub.api.contexts.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "creath_companies")
@Getter
@Setter
@NoArgsConstructor
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name", unique = true)
    private String name;
    @OneToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    private User owner;

    @Builder
    public Company(UUID id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
}
