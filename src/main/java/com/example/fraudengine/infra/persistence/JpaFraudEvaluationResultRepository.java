package com.example.fraudengine.infra.persistence;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFraudEvaluationResultRepository extends JpaRepository<FraudEvaluationResultEntity, UUID> {
  Optional<FraudEvaluationResultEntity> findByTransactionId(UUID transactionId);
  void deleteByTransactionId(UUID transactionId);
}
