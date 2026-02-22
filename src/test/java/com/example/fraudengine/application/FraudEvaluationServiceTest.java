package com.example.fraudengine.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.fraudengine.application.port.FraudEvaluationResultRepository;
import com.example.fraudengine.application.port.TransactionRepository;
import com.example.fraudengine.domain.model.FraudDecision;
import com.example.fraudengine.domain.model.FraudEvaluationResult;
import com.example.fraudengine.domain.model.Transaction;
import com.example.fraudengine.domain.rule.FraudRule;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FraudEvaluationServiceTest {

  @Mock private TransactionRepository transactionRepository;
  @Mock private FraudEvaluationResultRepository evaluationResultRepository;
  @Mock private FraudRule fraudRule;

  private FraudEvaluationService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    service =
        new FraudEvaluationService(
            transactionRepository, evaluationResultRepository, List.of(fraudRule));
  }

  @Test
  void evaluate_FlagsTransactionWhenRuleFails() {
    Transaction input =
        new Transaction(null, "acct-1", BigDecimal.TEN, "USD", "US", Instant.now());
    Transaction saved =
        new Transaction(
            UUID.randomUUID(), "acct-1", BigDecimal.TEN, "USD", "US", Instant.now());
    when(transactionRepository.save(input)).thenReturn(saved);
    when(fraudRule.isFraudulent(saved)).thenReturn(true);
    when(fraudRule.failureReason()).thenReturn("High amount");

    ArgumentCaptor<FraudEvaluationResult> resultCaptor =
        ArgumentCaptor.forClass(FraudEvaluationResult.class);
    when(evaluationResultRepository.save(resultCaptor.capture()))
        .thenAnswer(invocation -> invocation.getArgument(0));

    FraudEvaluationResult result = service.evaluation(input);

    assertThat(result.getDecision()).isEqualTo(FraudDecision.FLAGGED);
    assertThat(result.getReasons()).containsExactly("High amount");
    assertThat(resultCaptor.getValue().getTransaction()).isEqualTo(saved);
  }
}