package br.com.dancehub.api.event;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "creath_events")
@Getter
@Setter
public class EventEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "file_name")
    private String fileName;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "creath_events_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    @Column(name = "local")
    private String local;
    @Column(name = "time")
    private LocalDateTime time;


    @Builder
    public EventEntity(UUID id, String name, String description, String fileName, List<Category> categories, String local, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.fileName = fileName;
        this.categories = categories;
        this.local = local;
        this.time = time;
    }

    public EventEntity() {

    }
}
