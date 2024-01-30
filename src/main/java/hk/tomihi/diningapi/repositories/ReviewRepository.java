package hk.tomihi.diningapi.repositories;

import hk.tomihi.diningapi.model.Restaurant;
import hk.tomihi.diningapi.model.Review;
import hk.tomihi.diningapi.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByApprovedDateNullOrderByPostDateAsc();
    List<Review> findAllByUserOrderByPostDate(User user, Sort sort);
    List<Review> findAllByRestaurantOrderByPostDate(Restaurant user, Sort sort);

}
