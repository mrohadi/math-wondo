package math.wondo.repository;

import java.util.List;
import math.wondo.model.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

/** This interface allow us to store and retrieve attempt */
public interface MultiplicationResultAttemptRespository
        extends CrudRepository<MultiplicationResultAttempt, Long> {

    /**
     * @return the latest 5 attempts for a given user, identified by their alias
     */
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
