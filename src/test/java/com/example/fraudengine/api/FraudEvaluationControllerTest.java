package com.example.fraudengine.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.fraudengine.api.mapper.FraudEvaluationResponseMapper;
import com.example.fraudengine.api.mapper.TransactionRequestMapper;
import com.example.fraudengine.application.FraudEvaluationService;
import com.example.fraudengine.domain.model.FraudDecision;
import com.example.fraudengine.domain.model.FraudEvaluationResult;
import com.example.fraudengine.domain.model.Transaction;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class FraudEvaluationControllerTest {

  private MockMvc mockMvc;
  private FraudEvaluationService fraudEvaluationService;

  @BeforeEach
  void setUp() {
    fraudEvaluationService = Mockito.mock(FraudEvaluationService.class);
    FraudEvaluationController controller =
        new FraudEvaluationController(
            fraudEvaluationService,
            new TransactionRequestMapper(),
            new FraudEvaluationResponseMapper());
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void evaluate_ReturnsResponsePayload() throws Exception {
    Transaction transaction =
        new Transaction(
            UUID.randomUUID(), "acct-123", BigDecimal.TEN, "USD", "US", Instant.now());
    FraudEvaluationResult result =
        new FraudEvaluationResult(
            UUID.randomUUID(), transaction, FraudDecision.APPROVED, List.of(), Instant.now());

    Mockito.when(fraudEvaluationService.evaluation(ArgumentMatchers.any()))
        .thenReturn(result);

    mockMvc
        .perform(
            post("/api/v1/fraud/evaluate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"accountId\":\"acct-123\",\"amount\":10,"
                        + "\"currency\":\"USD\",\"country\":\"US\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.decision").value("APPROVED"))
        .andExpect(jsonPath("$.transactionSummary.accountId").value("acct-123"));
  }
}