package com.example.fraudengine.api.mapper;

import com.example.fraudengine.api.FraudEvaluationResponse;
import com.example.fraudengine.api.TransactionSummary;
import com.example.fraudengine.domain.model.FraudEvaluationResult;
import org.springframework.stereotype.Component;

@Component
public class FraudEvaluationResponseMapper {

  public FraudEvaluationResponse toResponse(FraudEvaluationResult result) {
    return new FraudEvaluationResponse(
      result.getId(),
      result.getDecision(),
      result.getReasons(),
      result.getEvaluatedAt(),
      new TransactionSummary(
        result.getTransaction().getId(),
        result.getTransaction().getAccountId(),
        result.getTransaction().getAmount(),
        result.getTransaction().getCurrency(),
        result.getTransaction().getCountry(),
        result.getTransaction().getOccuredAt()
      )
    );
  }
}
