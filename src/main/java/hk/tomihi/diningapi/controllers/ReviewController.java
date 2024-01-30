package hk.tomihi.diningapi.controllers;


import hk.tomihi.diningapi.model.Restaurant;
import hk.tomihi.diningapi.model.Review;
import hk.tomihi.diningapi.model.User;
import hk.tomihi.diningapi.repositories.RestaurantRepository;
import hk.tomihi.diningapi.repositories.ReviewRepository;
import hk.tomihi.diningapi.repositories.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Iterable<Review> getMyReviews(Principal principal) throws AuthenticationException {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(AuthenticationException::new);
        return reviewRepository.findAllByUserOrderByPostDate(user, Sort.by(Sort.Direction.DESC));
    }

    @GetMapping("/user/{id}")
    public Iterable<Review> getUserReviews(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        User user = userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return reviewRepository.findAllByUserOrderByPostDate(user, Sort.by(Sort.Direction.DESC));
    }

    @GetMapping("/restaurant/{id}")
    public Iterable<Review> getRestaurantReviews(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return reviewRepository.findAllByRestaurantOrderByPostDate(restaurant, Sort.by(Sort.Direction.DESC));
    }

    @GetMapping("/post")
    public Review postUserReview(@RequestParam Review review) {
        return reviewRepository.save(review);
    }
}
