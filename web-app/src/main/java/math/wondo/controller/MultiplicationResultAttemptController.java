package math.wondo.controller;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import math.wondo.model.MultiplicationResultAttempt;
import math.wondo.service.MultiplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("results")
public class MultiplicationResultAttemptController {

    @Autowired
    private MultiplicationService multiplicationService;

    // Here we'll implement our POST later
    @RequiredArgsConstructor
    @NoArgsConstructor(force = true)
    @Getter
    public static final class ResultResponse {
        private final boolean correct;
    }

    @PostMapping
    public ResponseEntity<ResultResponse> postResult(
            @RequestBody MultiplicationResultAttempt attempt) {
        return ResponseEntity.ok(new ResultResponse(multiplicationService.checkAttempt(attempt)));
    }

    @GetMapping
    public ResponseEntity<List<MultiplicationResultAttempt>> getStatistics(
            @RequestParam("alias") String userAlias) {
        return ResponseEntity.ok(multiplicationService.getStatsForUser(userAlias));
    }
}
