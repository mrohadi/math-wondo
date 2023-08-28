package math.wondo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import math.wondo.model.Multiplication;
import math.wondo.model.MultiplicationResultAttempt;
import math.wondo.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MultiplicationServiceTest {
    
    @Mock
    private RandomGeneratorService randomGeneratorService;

    private Multiplication multiplication;
    private User user;

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
        int result = factorA * factorB;
        given(randomGeneratorService.generateRandomFactor())
            .willReturn(factorA, factorB);

        // when
        Multiplication multiplication = multiplicationService.createRandomMultiplication();
        
        // then
        assertThat(multiplication.getFactorA()).isEqualTo(factorA);
        assertThat(multiplication.getFactorB()).isEqualTo(factorB);
        assertThat(multiplication.getResult()).isEqualTo(result);
    }

    @Test
    public void checkCorrectAttemptTest() {
        //given
        int attempt = 3000;
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(user, multiplication, attempt);
        
        //when
        boolean result = multiplicationService.checkAttempt(resultAttempt);
        
        //then
        assertTrue(result);
    }
    
    @Test
    public void checkWrongAttemptTest() {
        //given
        int attempt = 3010;
        MultiplicationResultAttempt resultAttempt = new MultiplicationResultAttempt(user, multiplication, attempt);
        
        //when
        boolean result = multiplicationService.checkAttempt(resultAttempt);
        
        //then
        assertFalse(result);
    }

}
