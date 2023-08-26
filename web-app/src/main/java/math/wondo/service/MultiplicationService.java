package math.wondo.service;

import math.wondo.model.Multiplication;

public interface MultiplicationService {
    
    /**
     * Create random multiplication object with two randomly-generated factors
     * btween 11 and 99
     */
    Multiplication createRandomMultiplication();
}
