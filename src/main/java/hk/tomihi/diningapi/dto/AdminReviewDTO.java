package hk.tomihi.diningapi.dto;

import hk.tomihi.diningapi.model.Review;
import hk.tomihi.diningapi.model.Score;
import lombok.Data;


@Data

public class AdminReviewDTO {
    private Long id;
    private String username;

    private String restaurant;

    private String review;

    private Score score;

    private Score atmosphereScore;

    private Score foodScore;

    private String posted;
    private String approved;

    public AdminReviewDTO(Review review) {
        this.id = review.getId();
        this.username = review.getUser().getUsername();
        this.restaurant = review.getRestaurant().getTitle();
        this.review = review.getReview();
        this.score = review.getScore();
        this.atmosphereScore = review.getAtmosphereScore();
        this.foodScore = review.getFoodScore();
        this.posted = review.getPostDate() == null ? null : review.getPostDate().toString();
        this.approved = review.getApprovedDate() == null ? null : review.getApprovedDate().toString();
    }
}
