package com.example.fraudengine.infra.persistence;

import com.example.fraudengine.domain.model.FraudDecision;
import com.example.fraudengine.domain.model.Transaction;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fraud_evaluations")
public class FraudEvaluationResultEntity {

  @Id
  @GeneratedValue
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "transaction_id", nullable = false)
  private TransactionEntity transaction;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private FraudDecision decision;

  @ElementCollection
  @CollectionTable(name = "fraud_evaluation_reasons", joinColumns = @JoinColumn(name = "evaluation_id"))
  @Column(name = "reason", nullable = false)
  private List<String> reasons;

  @Column(name = "evaluated_at", nullable = false)
  private Instant evaluatedAt;

  protected FraudEvaluationResultEntity() { }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public TransactionEntity getTransaction() {
    return transaction;
  }

  public void setTransaction(TransactionEntity transaction) {
    this.transaction = transaction;
  }

  public FraudDecision getDecision() {
    return decision;
  }

  public void setDecision(FraudDecision decision) {
    this.decision = decision;
  }

  public List<String> getReasons() {
    return reasons;
  }

  public void setReasons(List<String> reasons) {
    this.reasons = reasons;
  }

  public Instant getEvaluatedAt() {
    return evaluatedAt;
  }

  public void setEvaluatedAt(Instant evaluatedAt) {
    this.evaluatedAt = evaluatedAt;
  }
}
