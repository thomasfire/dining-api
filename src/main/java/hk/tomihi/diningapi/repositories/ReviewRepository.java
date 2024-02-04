package hk.tomihi.diningapi.repositories;

import hk.tomihi.diningapi.model.Restaurant;
import hk.tomihi.diningapi.model.Review;
import hk.tomihi.diningapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByApprovedDateNullOrderByPostDateAsc();
    List<Review> findAllByUserAndApprovedDateNotNullOrderByPostDateDesc(User user);
    List<Review> findAllByRestaurantAndApprovedDateNotNullOrderByPostDateDesc(Restaurant user);

}
