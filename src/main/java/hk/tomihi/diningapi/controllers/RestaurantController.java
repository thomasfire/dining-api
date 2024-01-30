package hk.tomihi.diningapi.controllers;

import hk.tomihi.diningapi.model.Restaurant;
import hk.tomihi.diningapi.repositories.RestaurantRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    final private RestaurantRepository restaurantRepository;

    public RestaurantController(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @GetMapping
    public Iterable<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

}