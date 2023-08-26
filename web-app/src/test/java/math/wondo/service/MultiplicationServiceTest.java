package math.wondo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import math.wondo.model.Multiplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MultiplicationServiceTest {
    
    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Spy
    @InjectMocks
    private MultiplicationServiceImpl multiplicationService;

    // @BeforeEach
    // public void initAll() {
    //     multiplicationService = new MultiplicationServiceImpl(randomGeneratorService);
    // }

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

}
