package math.wondo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import math.wondo.model.Multiplication;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    @Autowired
    private RandomGeneratorService randomGeneratorService;

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }
    
}
