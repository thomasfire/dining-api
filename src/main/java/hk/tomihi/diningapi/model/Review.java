package hk.tomihi.diningapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;


@Getter
@Entity
@Table(name = "REVIEWS")
public class Review {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private User user;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESTAURANT_ID", nullable = false, updatable = false)
    private Restaurant restaurant;

    @Setter
    @Column(name = "REVIEW", nullable = false)
    private String review;

    @Setter
    @Column(name = "SCORE", nullable = true)
    @Enumerated(EnumType.ORDINAL)
    private Score score;

    @Setter
    @Column(name = "ATMOSPHERE", nullable = true)
    @Enumerated(EnumType.ORDINAL)
    private Score atmosphereScore;

    @Setter
    @Column(name = "FOOD", nullable = true)
    @Enumerated(EnumType.ORDINAL)
    private Score foodScore;

    @Setter
    @Column(name = "POSTED", nullable = false, updatable = false)
    private Date postDate;

    @Setter
    @Column(name = "APPROVED", nullable = true)
    private Date approvedDate;

    public Review(User user, Restaurant restaurant, String review, Score score, Score atmosphereScore, Score foodScore) {
        this.user = user;
        this.restaurant = restaurant;
        this.review = review;
        this.score = score;
        this.atmosphereScore = atmosphereScore;
        this.foodScore = foodScore;
        this.postDate = Date.valueOf(LocalDate.now());
    }

    public Review() {

    }
}
