package br.com.dancehub.api.contexts.invite;

import br.com.dancehub.api.contexts.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "creath_invites")
@Getter
@Setter
@NoArgsConstructor
public class Invite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "company_id")
    private UUID companyId;
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, targetEntity = User.class)
    @JoinColumn(name = "guest_id")
    private User guest;
    @Column(name = "key")
    private String key;
    @Column(name = "sent")
    private Boolean sent;

    @Builder
    public Invite(UUID id, UUID companyId, User guest, String key, boolean sent) {
        this.id = id;
        this.companyId = companyId;
        this.guest = guest;
        this.key = key;
        this.sent = sent;
    }
}
