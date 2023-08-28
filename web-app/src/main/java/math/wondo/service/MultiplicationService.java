package math.wondo.service;

import math.wondo.model.Multiplication;
import math.wondo.model.MultiplicationResultAttempt;

public interface MultiplicationService {
    
    /**
     * Generates a random {@link Multiplication} object.
     * @return a {@link Multiplication} of randomly generated numbers
     */
    Multiplication createRandomMultiplication();

    /**
     * @return true if the attempt matches the result of the multiplication,
     * false otherwise
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt); 
}
