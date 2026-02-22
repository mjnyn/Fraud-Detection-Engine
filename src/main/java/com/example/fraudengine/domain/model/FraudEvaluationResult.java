package com.example.fraudengine.domain.model;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.processing.Generated;

public class FraudEvaluationResult {

  private final UUID id;
  private final Transaction transaction;
  private final FraudDecision decision;
  private final List<String> reasons;
  private final Instant evaluatedAt;

  public FraudEvaluationResult(UUID id, Transaction transaction, FraudDecision decision, List<String> reasons, Instant evaluatedAt) {
    this.id = id;
    this.transaction = Objects.requireNonNull(transaction, "transaction cannot be null");
    this.decision = Objects.requireNonNull(decision, "decision cannot be null");
    this.reasons = Objects.requireNonNull(reasons, "reasons cannot be null");
    this.evaluatedAt = evaluatedAt;
  }

  public UUID getId() {
    return id;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public FraudDecision getDecision() {
    return decision;
  }

  public List<String> getReasons() {
    return reasons;
  }

  public Instant getEvaluatedAt() {
    return evaluatedAt;
  }
}
