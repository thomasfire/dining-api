package hk.tomihi.diningapi.repositories;

import hk.tomihi.diningapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String passwordHash);
    Optional<User> findByUsername(String username);
}
