package hk.tomihi.diningapi.controllers;

import hk.tomihi.diningapi.model.Restaurant;
import hk.tomihi.diningapi.model.Review;
import hk.tomihi.diningapi.repositories.RestaurantRepository;
import hk.tomihi.diningapi.repositories.ReviewRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

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
    public Iterable<Review> getPendingReviews() {
        return reviewRepository.findAllByApprovedDateNullOrderByPostDateAsc();
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Review approveReview(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        review.setApprovedDate(Date.valueOf(LocalDate.now()));
        return reviewRepository.save(review);
    }

    @PostMapping("/restaurant/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Restaurant addRestaurant(@RequestParam Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}
