package hk.tomihi.diningapi.repositories;

import hk.tomihi.diningapi.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

}
