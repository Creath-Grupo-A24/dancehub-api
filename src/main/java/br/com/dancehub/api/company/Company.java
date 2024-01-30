package br.com.dancehub.api.company;


import br.com.dancehub.api.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private List<User> dancers;
    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private List<User> choreographer;

    @Builder
    public Company(UUID id, String name, User owner, List<User> dancers, List<User> choreographer) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.dancers = dancers == null ? new ArrayList<>() : dancers;
        this.choreographer = choreographer == null ? new ArrayList<>() : choreographer;
    }
}
