package br.com.dancehub.api.contexts.subscription;

import br.com.dancehub.api.contexts.event.Category;
import br.com.dancehub.api.contexts.event.EventEntity;
import br.com.dancehub.api.contexts.user.User;
import br.com.dancehub.api.contexts.user.role.RoleType;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "creath_users_subscription",
            joinColumns = @JoinColumn(name = "subscription_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> staff;

    @Builder
    public SubscriptionEntity(UUID id, String name, Category category, String description, LocalDateTime time, EventEntity event, List<User> staff) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.time = time;
        this.event = event;
        this.staff = staff;
    }

    public SubscriptionEntity() {
    }

    // manager único
    public User getManager() {
        return this.staff.stream()
                .filter(user -> user.getRoles()
                        .stream()
                        .anyMatch(role -> role.getType().equals(RoleType.MANAGER)))
                .findFirst().orElse(null);
    }

    // vários coreógrafos
    public List<User> getTeacher() {
        return this.staff.stream()
                .filter(user -> user.getRoles()
                        .stream()
                        .anyMatch(role -> role.getType().equals(RoleType.TEACHER))).toList();
    }

    // vários dançarinos
    public List<User> getDancers(){
        return this.staff.stream()
                .filter(user -> user.getRoles()
                        .stream()
                        .anyMatch(role -> role.getType().equals(RoleType.DANCER))).toList();
    }
}
