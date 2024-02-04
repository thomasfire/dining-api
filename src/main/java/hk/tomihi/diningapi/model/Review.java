package hk.tomihi.diningapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


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
    @Column(name = "SCORE")
    @Enumerated(EnumType.ORDINAL)
    private Score score;

    @Setter
    @Column(name = "ATMOSPHERE")
    @Enumerated(EnumType.ORDINAL)
    private Score atmosphereScore;

    @Setter
    @Column(name = "FOOD")
    @Enumerated(EnumType.ORDINAL)
    private Score foodScore;

    @Setter
    @Column(name = "POSTED", nullable = false, updatable = false)
    private Timestamp postDate;

    @Setter
    @Column(name = "APPROVED")
    private Timestamp approvedDate;

    public Review(User user, Restaurant restaurant, String review, Score score, Score atmosphereScore, Score foodScore) {
        this.user = user;
        this.restaurant = restaurant;
        this.review = review;
        this.score = score;
        this.atmosphereScore = atmosphereScore;
        this.foodScore = foodScore;
        this.postDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public Review() {

    }
}
