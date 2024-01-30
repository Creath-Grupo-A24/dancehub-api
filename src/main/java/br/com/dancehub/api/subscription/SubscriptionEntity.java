package br.com.dancehub.api.subscription;

import br.com.dancehub.api.event.Category;
import br.com.dancehub.api.event.EventEntity;
import br.com.dancehub.api.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creath_subscription")
@Getter
@Setter
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "description")
    private String description;
    @Column(name = "time")
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private EventEntity event;
    @OneToOne
    @JoinColumn(name = "director_id")
    private User director;
    @ManyToOne
    @JoinColumn(name = "choreographer_id")
    private User choreographer;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "creath_users_subscription",
            joinColumns = @JoinColumn(name = "subscription_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> dancers;

    @Builder
    public SubscriptionEntity(UUID id, String name, Category category, String description, LocalDateTime time, EventEntity event, User director, User choreographer, List<User> dancers) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.time = time;
        this.event = event;
        this.director = director;
        this.choreographer = choreographer;
        this.dancers = dancers;
    }

    public SubscriptionEntity() {
    }
}
