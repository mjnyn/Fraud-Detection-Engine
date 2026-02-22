package com.example.fraudengine.domain.rule;

import com.example.fraudengine.domain.model.Transaction;
import java.math.BigDecimal;

public class HighAmount implements FraudRule {

  private final BigDecimal amount;

  public HighAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public boolean isFraudulent(Transaction transaction) {
    return transaction.getAmount().compareTo(amount) > 0;
  }

  @Override
  public String ruleName() {
    return "High Amount";
  }

  @Override
  public String failureReason() {
    return String.format("Amount exceeds the threshold of %s", amount);
  }
}
