package math.wondo.repository;

import math.wondo.model.Multiplication;
import org.springframework.data.repository.CrudRepository;

/** This interface allow us to retrieve {@link Multiplication} */
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}
