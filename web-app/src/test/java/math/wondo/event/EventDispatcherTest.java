package math.wondo.event;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@ExtendWith(MockitoExtension.class)
public class EventDispatcherTest {

    private String multiplicationExchange = "multiplication_exchange";
    private String multiplicationSolvedRoutingKey = "multiplication.solved";

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Spy
    @InjectMocks
    private EventDispatcher eventDispatcher;

    @Test
    public void sendMessageShouldBeSuccess() {
        MultiplicationSolvedEvent multiplicationSolvedEvent = new MultiplicationSolvedEvent((long) 1, (long) 1, true);

        rabbitTemplate.convertAndSend(
                multiplicationExchange, multiplicationSolvedRoutingKey, multiplicationSolvedEvent);

        assertDoesNotThrow(
                () -> {
                    eventDispatcher.send(multiplicationSolvedEvent);

                    verify(rabbitTemplate, times(1))
                            .convertAndSend(
                                    multiplicationExchange,
                                    multiplicationSolvedRoutingKey,
                                    multiplicationSolvedEvent);
                });
    }
}
