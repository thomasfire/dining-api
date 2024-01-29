package hk.tomihi.diningapi.repositories;

import hk.tomihi.diningapi.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByApprovedDateNullOrderByPostDateAsc();

}
