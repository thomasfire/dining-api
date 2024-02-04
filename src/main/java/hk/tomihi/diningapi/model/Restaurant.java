package hk.tomihi.diningapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "RESTAURANT")
public class Restaurant {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @Column(name = "TITLE", nullable = false, unique = true)
    private String title;

    @Setter
    @Column(name = "DESCRIPTION")
    private String description;

    public Restaurant() {
    }
}
