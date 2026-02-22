package com.example.fraudengine.api;

import com.example.fraudengine.domain.model.FraudDecision;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record FraudEvaluationResponse (
  UUID responseId,
  FraudDecision decision,
  List<String> reasons,
  Instant evaluatedAt,
  TransactionSummary transactionSummary
) {}


