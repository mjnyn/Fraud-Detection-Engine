package com.example.fraudengine.application;

import com.example.fraudengine.application.port.FraudEvaluationResultRepository;
import com.example.fraudengine.application.port.TransactionRepository;
import com.example.fraudengine.domain.model.FraudDecision;
import com.example.fraudengine.domain.model.FraudEvaluationResult;
import com.example.fraudengine.domain.model.Transaction;
import com.example.fraudengine.domain.rule.FraudRule;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FraudEvaluationService {

  private final TransactionRepository transactionRepository;
  private final FraudEvaluationResultRepository fraudEvaluationResultRepository;
  private List<FraudRule> ruleList = new ArrayList<>();

  public FraudEvaluationService(TransactionRepository transactionRepository, FraudEvaluationResultRepository fraudEvaluationResultRepository, List<FraudRule> ruleList) {
    this.transactionRepository = transactionRepository;
    this.fraudEvaluationResultRepository = fraudEvaluationResultRepository;
    this.ruleList = ruleList;
  }

  public FraudEvaluationResult evaluation (Transaction transaction) {
    Transaction savedTransaction = transactionRepository.save(transaction);

    List<String> reasons = new ArrayList<>();
    for (FraudRule rule : ruleList) {
      if (rule.isFraudulent(savedTransaction)) {
        reasons.add(rule.failureReason());
      }
    }
    FraudDecision decision = reasons.isEmpty() ? FraudDecision.APPROVED : FraudDecision.FLAGGED;

    FraudEvaluationResult evaluationResult = new FraudEvaluationResult(null, savedTransaction, decision, reasons, Instant.now());
    return fraudEvaluationResultRepository.save(evaluationResult);
  }
}
