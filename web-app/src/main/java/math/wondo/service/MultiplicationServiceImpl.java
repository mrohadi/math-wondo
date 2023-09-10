package math.wondo.service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import math.wondo.event.EventDispatcher;
import math.wondo.event.MultiplicationSolvedEvent;
import math.wondo.model.Multiplication;
import math.wondo.model.MultiplicationResultAttempt;
import math.wondo.model.User;
import math.wondo.repository.MultiplicationResultAttemptRespository;
import math.wondo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    @Autowired
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MultiplicationResultAttemptRespository attemptRepository;

    @Autowired
    private EventDispatcher eventDispatcher;

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }

    @Override
    @Transactional
    public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
        // Check if the user already exists for that alias
        Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());

        // Avoid 'hack' attempts
        Assert.isTrue(!resultAttempt.isCorrect(), "You can't send an attempt marked as correct!");

        // Check if the attempt is correct
        boolean isCorrect = resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA()
                * resultAttempt.getMultiplication().getFactorB();

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user.orElse(resultAttempt.getUser()),
                resultAttempt.getMultiplication(),
                resultAttempt.getResultAttempt(),
                isCorrect);

        // Stores the attempt
        attemptRepository.save(checkedAttempt);

        // Communicate the result via Event
        eventDispatcher.send(
                new MultiplicationSolvedEvent(
                        checkedAttempt.getId(), checkedAttempt.getUser().getId(), checkedAttempt.isCorrect()));

        return isCorrect;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }
}
