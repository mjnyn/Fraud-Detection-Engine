package com.example.fraudengine.infra.persistence;

import com.example.fraudengine.domain.model.FraudEvaluationResult;

public class FraudEvaluationResultEntityMapper {
  private FraudEvaluationResultEntityMapper() { }

  public static FraudEvaluationResultEntity toEntity(FraudEvaluationResult fraudEvaluationResult) {
    if (fraudEvaluationResult == null) {
      return null;
    }

    FraudEvaluationResultEntity fraudEvaluationResultEntity = new FraudEvaluationResultEntity();
    fraudEvaluationResultEntity.setId(fraudEvaluationResult.getId());
    fraudEvaluationResultEntity.setTransaction(TransactionEntityMapper.toEntity(fraudEvaluationResult.getTransaction()));
    fraudEvaluationResultEntity.setDecision(fraudEvaluationResult.getDecision());
    fraudEvaluationResultEntity.setReasons(fraudEvaluationResult.getReasons());
    fraudEvaluationResultEntity.setEvaluatedAt(fraudEvaluationResult.getEvaluatedAt());
    return fraudEvaluationResultEntity;
  }

  public static FraudEvaluationResult toDomain(FraudEvaluationResultEntity fraudEvaluationResultEntity) {
    if (fraudEvaluationResultEntity == null) {
      return null;
    }

    return new FraudEvaluationResult(
      fraudEvaluationResultEntity.getId(),
      TransactionEntityMapper.toDomain(fraudEvaluationResultEntity.getTransaction()),
      fraudEvaluationResultEntity.getDecision(),
      fraudEvaluationResultEntity.getReasons(),
      fraudEvaluationResultEntity.getEvaluatedAt()
    );
  }
}
