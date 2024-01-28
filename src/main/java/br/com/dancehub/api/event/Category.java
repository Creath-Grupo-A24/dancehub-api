package br.com.dancehub.api.event;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "creath_categories")
@Getter
@Setter
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CategoryType type;

    @Builder
    public Category(Integer id, CategoryType type) {
        this.id = id;
        this.type = type;
    }

    public Category() {

    }
}
