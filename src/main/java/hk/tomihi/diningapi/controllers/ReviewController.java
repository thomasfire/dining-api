package hk.tomihi.diningapi.controllers;


import hk.tomihi.diningapi.dto.AdminReviewDTO;
import hk.tomihi.diningapi.dto.ReviewDTO;
import hk.tomihi.diningapi.model.Restaurant;
import hk.tomihi.diningapi.model.Review;
import hk.tomihi.diningapi.model.User;
import hk.tomihi.diningapi.repositories.RestaurantRepository;
import hk.tomihi.diningapi.repositories.ReviewRepository;
import hk.tomihi.diningapi.repositories.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.security.Principal;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    final private ReviewRepository reviewRepository;
    final private UserRepository userRepository;
    final private RestaurantRepository restaurantRepository;

    public ReviewController(final ReviewRepository reviewRepository, final UserRepository userRepository, final RestaurantRepository restaurantRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public Iterable<AdminReviewDTO> getMyReviews(Principal principal) throws AuthenticationException {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(AuthenticationException::new);
        return reviewRepository.findAllByUserAndApprovedDateNotNullOrderByPostDateDesc(user).stream().map(AdminReviewDTO::new).toList();
    }

    @GetMapping("/user/{id}")
    public Iterable<AdminReviewDTO> getUserReviews(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return reviewRepository.findAllByUserAndApprovedDateNotNullOrderByPostDateDesc(user).stream().map(AdminReviewDTO::new).toList();
    }

    @GetMapping("/restaurant/{id}")
    public Iterable<AdminReviewDTO> getRestaurantReviews(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return reviewRepository.findAllByRestaurantAndApprovedDateNotNullOrderByPostDateDesc(restaurant).stream().map(AdminReviewDTO::new).toList();
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('USER')")
    public Review postUserReview(@RequestBody ReviewDTO reviewDTO, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(""));
        Restaurant restaurant = restaurantRepository.findById(reviewDTO.getRestaurantId()).orElseThrow(() -> new ArrayIndexOutOfBoundsException(""));
        Review review = new Review(
                user,
                restaurant,
                reviewDTO.getReview(),
                ReviewDTO.stringToScore(reviewDTO.getScore()),
                ReviewDTO.stringToScore(reviewDTO.getAtmosphereScore()),
                ReviewDTO.stringToScore(reviewDTO.getFoodScore())
        );
        return reviewRepository.save(review);
    }
}
