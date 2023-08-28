package math.wondo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Indetifires the atttempt from a {@link User} to solve a
 * {@link Multiplication}
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class MultiplicationResultAttempt {

    private final User user;
    private final Multiplication multiplication;
    private final int resultAttempt;

    // Empty constructor for JSON deserialization
    protected MultiplicationResultAttempt() {
        user = null;
        multiplication = null;
        resultAttempt = -1;
    }
}
