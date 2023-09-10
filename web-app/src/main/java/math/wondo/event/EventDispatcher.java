package math.wondo.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** Handles the communication with the Event Bus */
@Component
public class EventDispatcher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // The exchange to use to send anyting related
    // to Multiplication
    @Value("${multiplication.exchange}")
    private String multiplicationExchange;

    // The routing key to use to send this pacticular event
    @Value("${multiplication.solved.key}")
    private String multiplicationSolvedRoutingKey;

    public void send(final MultiplicationSolvedEvent multiplicationSolvedEvent) {
        rabbitTemplate.convertAndSend(
                multiplicationExchange, multiplicationSolvedRoutingKey, multiplicationSolvedEvent);
    }
}
