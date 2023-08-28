package math.wondo.controller;

import math.wondo.model.Multiplication;
import math.wondo.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/multiplication/")
public class MultiplicationController {

    @Autowired
    private MultiplicationService multiplicationService;

    @GetMapping("random")
    public ResponseEntity<Multiplication> getRandomMultiplication() {
        return ResponseEntity.ok(multiplicationService.createRandomMultiplication());
    }
}
