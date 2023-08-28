package math.wondo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import math.wondo.model.Multiplication;
import math.wondo.service.MultiplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class MultiplicationControllerTest {

    @Mock
    private MultiplicationService multiplicationService;

    @InjectMocks
    private MultiplicationController multiplicationController;

    @BeforeEach
    public void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getRandomMultiplicationTest() throws Exception {
        // given
        given(multiplicationService.createRandomMultiplication())
                .willReturn(new Multiplication(70, 20));

        // when
        var result = multiplicationController.getRandomMultiplication();

        // then
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(new Multiplication(70, 20));
    }
}
