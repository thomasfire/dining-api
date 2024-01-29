package hk.tomihi.diningapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Setter
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    public Restaurant(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Restaurant(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Restaurant() {
    }
}
