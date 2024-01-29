package hk.tomihi.diningapi.repositories;

import hk.tomihi.diningapi.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Long> {
}
