package math.wondo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This class represents a Multiplication in our application
 */
@RequiredArgsConstructor
@Getter 
@ToString
@EqualsAndHashCode
@Entity(name = "Multiplication")
public class Multiplication {
    
    @Id
    @GeneratedValue
    @Column(name = "MultiplicationId")
    private Long id;
    
    @Column(name = "FactorA")
    private int factorA;
    
    @Column(name = "FactorB")
    private int factorB;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }
}
