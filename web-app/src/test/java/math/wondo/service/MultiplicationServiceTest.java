package math.wondo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
import math.wondo.event.EventDispatcher;
import math.wondo.event.MultiplicationSolvedEvent;
import math.wondo.model.Multiplication;
import math.wondo.model.MultiplicationResultAttempt;
import math.wondo.model.User;
import math.wondo.repository.MultiplicationResultAttemptRespository;
import math.wondo.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MultiplicationServiceTest {

    private Multiplication multiplication;
    private User user;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MultiplicationResultAttemptRespository attemptRepository;

    @Mock
    private EventDispatcher eventDispatcher;

    @Spy
    @InjectMocks
    private MultiplicationServiceImpl multiplicationService;

    @BeforeEach
    public void initAll() {
        multiplication = new Multiplication(50, 60);
        user = new User("mrohadi");
    }

    @Test
    public void createRandomMultiplicationTest() {
        // give
        // our mocked random generator service call will return
        // first 50, then 30
        int factorA = 50;
        int factorB = 30;
        given(randomGeneratorService.generateRandomFactor()).willReturn(factorA, factorB);

        // when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        // then
        assertThat(multiplication.getFactorA()).isEqualTo(factorA);
        assertThat(multiplication.getFactorB()).isEqualTo(factorB);
    }

    @Test
    public void shouldThrowIllegalArgumentException() {
        // given
        given(userRepository.findByAlias("mrohadi")).willReturn(Optional.empty());

        int userAttempt = 3000;
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(user, multiplication, userAttempt,
                true);

        // then
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    multiplicationService.checkAttempt(resultAttempt);
                });
    }

    @Test
    public void checkCorrectAttemptTest() {
        // given
        int userAttempt = 3000;
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(user, multiplication, userAttempt,
                false);
        MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, userAttempt,
                true);
        given(userRepository.findByAlias("mrohadi")).willReturn(Optional.empty());

        // when
        boolean result = multiplicationService.checkAttempt(resultAttempt);

        MultiplicationSolvedEvent multiplicationSolvedEvent = new MultiplicationSolvedEvent(
                verifiedAttempt.getId(),
                verifiedAttempt.getUser().getId(),
                verifiedAttempt.isCorrect());

        // then
        assertTrue(result);
        verify(attemptRepository).save(verifiedAttempt);
        verify(eventDispatcher).send(multiplicationSolvedEvent);
    }

    @Test
    public void checkWrongAttemptTest() {
        // given
        int attempt = 3010;
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(user, multiplication, attempt,
                false);
        given(userRepository.findByAlias("mrohadi")).willReturn(Optional.empty());

        // when
        boolean result = multiplicationService.checkAttempt(resultAttempt);

        // then
        assertFalse(result);
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    public void getStatusForUser_ShouldReturnExpectedResult() {
        // given
        Multiplication multiplication = new Multiplication(50, 60);
        User user = new User("mrohadi");
        MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
        MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);
        List<MultiplicationResultAttempt> expected = Lists.newArrayList(attempt1, attempt2);

        given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("mrohadi")).willReturn(expected);

        // when
        List<MultiplicationResultAttempt> actual = multiplicationService.getStatsForUser("mrohadi");

        // then
        assertEquals(expected, actual);
    }
}
