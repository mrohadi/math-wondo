package math.wondo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Entity(name = "MultiplicationResultAttempt")
public final class MultiplicationResultAttempt {

    @Id
    @GeneratedValue
    @Column(name = "MultiplicationResultAttemptId")
    private Long id; 
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "UserId")
    private final User user;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "MultiplicationId")
    private final Multiplication multiplication;
    
    @Column(name = "ResultAttempt")
    private final int resultAttempt;

    @Column(name = "IsCorrect")
    private final boolean isCorrect;

    // Empty constructor for JSON deserialization
    protected MultiplicationResultAttempt() {
        user = null;
        multiplication = null;
        resultAttempt = -1;
        isCorrect = false;
    }
}
