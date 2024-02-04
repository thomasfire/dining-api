package hk.tomihi.diningapi.controllers;

import hk.tomihi.diningapi.dto.AdminReviewDTO;
import hk.tomihi.diningapi.model.Restaurant;
import hk.tomihi.diningapi.model.Review;
import hk.tomihi.diningapi.repositories.RestaurantRepository;
import hk.tomihi.diningapi.repositories.ReviewRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin")
public class AdminController {
    final private ReviewRepository reviewRepository;
    final private RestaurantRepository restaurantRepository;


    public AdminController(final ReviewRepository reviewRepository, final RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<AdminReviewDTO> getPendingReviews() {
        return reviewRepository.findAllByApprovedDateNullOrderByPostDateAsc().stream().map(AdminReviewDTO::new).toList();
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminReviewDTO approveReview(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        review.setApprovedDate(Timestamp.valueOf(LocalDateTime.now()));
        return new AdminReviewDTO(reviewRepository.save(review));
    }

    @PostMapping("/restaurant/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
