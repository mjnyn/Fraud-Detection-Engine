package com.example.fraudengine.infra.persistence.adapter;

import com.example.fraudengine.application.port.FraudEvaluationResultRepository;
import com.example.fraudengine.domain.model.FraudEvaluationResult;
import com.example.fraudengine.infra.persistence.FraudEvaluationResultEntity;
import com.example.fraudengine.infra.persistence.FraudEvaluationResultEntityMapper;
import com.example.fraudengine.infra.persistence.JpaFraudEvaluationResultRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class JpaFraudEvaluationResultRepositoryAdapter implements FraudEvaluationResultRepository {

  private final JpaFraudEvaluationResultRepository jpaFraudEvaluationResultRepository;

  public JpaFraudEvaluationResultRepositoryAdapter(
      JpaFraudEvaluationResultRepository jpaFraudEvaluationResultRepository) {
    this.jpaFraudEvaluationResultRepository = jpaFraudEvaluationResultRepository;
  }

  @Override
  public Optional<FraudEvaluationResult> findById(UUID id) {
    return jpaFraudEvaluationResultRepository
        .findById(id)
        .map(FraudEvaluationResultEntityMapper::toDomain);
  }

  @Override
  public Optional<FraudEvaluationResult> findByTransactionId(UUID transactionId) {
    return jpaFraudEvaluationResultRepository
        .findByTransactionId(transactionId)
        .map(FraudEvaluationResultEntityMapper::toDomain);
  }

  @Override
  @Transactional
  public FraudEvaluationResult save(FraudEvaluationResult fraudEvaluationResult) {
    FraudEvaluationResultEntity entity =
        FraudEvaluationResultEntityMapper.toEntity(fraudEvaluationResult);
    FraudEvaluationResultEntity saved = jpaFraudEvaluationResultRepository.save(entity);
    return FraudEvaluationResultEntityMapper.toDomain(saved);
  }

  @Override
  @Transactional
  public void deleteByTransactionId(UUID transactionId) {
    jpaFraudEvaluationResultRepository.deleteByTransactionId(transactionId);
  }
}