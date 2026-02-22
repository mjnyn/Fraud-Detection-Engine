package com.example.fraudengine.api;

import com.example.fraudengine.api.FraudEvaluationResponse;
import com.example.fraudengine.api.mapper.FraudEvaluationResponseMapper;
import com.example.fraudengine.api.mapper.TransactionRequestMapper;
import com.example.fraudengine.application.FraudEvaluationService;
import com.example.fraudengine.domain.model.FraudEvaluationResult;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud")
public class FraudEvaluationController {

  private final FraudEvaluationService fraudEvaluationService;
  private final TransactionRequestMapper transactionRequestMapper;
  private final FraudEvaluationResponseMapper responseMapper;

  public FraudEvaluationController(
          FraudEvaluationService fraudEvaluationService,
          TransactionRequestMapper transactionRequestMapper,
          FraudEvaluationResponseMapper responseMapper) {
    this.fraudEvaluationService = fraudEvaluationService;
    this.transactionRequestMapper = transactionRequestMapper;
    this.responseMapper = responseMapper;
  }

  @PostMapping("/evaluate")
  public ResponseEntity<FraudEvaluationResponse> evaluate(@RequestBody TransactionRequest request) {
    FraudEvaluationResult result =
            fraudEvaluationService.evaluation(transactionRequestMapper.toDomain(request));
    return ResponseEntity.ok(responseMapper.toResponse(result));
  }
}