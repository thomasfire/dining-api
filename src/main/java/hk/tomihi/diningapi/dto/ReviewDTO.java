package hk.tomihi.diningapi.dto;

import hk.tomihi.diningapi.model.Score;
import lombok.Data;

@Data
public class ReviewDTO {
    private Long userId;

    private Long restaurantId;

    private String review;

    private String score;

    private String atmosphereScore;

    private String foodScore;

    public static Score stringToScore(String score) {
        return Score.valueOf(score);

    }
}
