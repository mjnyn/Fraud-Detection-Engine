package com.example.fraudengine.application.port;

import com.example.fraudengine.domain.model.FraudEvaluationResult;
import java.util.Optional;
import java.util.UUID;

public interface FraudEvaluationResultRepository {
  Optional<FraudEvaluationResult> findById(UUID id);
  Optional<FraudEvaluationResult> findByTransactionId(UUID transactionId);

  FraudEvaluationResult save(FraudEvaluationResult fraudEvaluationResult);

  void deleteByTransactionId(UUID transactionId);
}
