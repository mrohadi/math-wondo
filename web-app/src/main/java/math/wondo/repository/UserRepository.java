package math.wondo.repository;

import java.util.Optional;
import math.wondo.model.User;
import org.springframework.data.repository.CrudRepository;

/** This interface allow us to store and retrieve {@link User} */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByAlias(String userAlias);
}
