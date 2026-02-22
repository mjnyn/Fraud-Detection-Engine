package com.example.fraudengine.domain.rule;

import com.example.fraudengine.domain.model.Transaction;
import java.util.List;

public interface FraudRule {

  boolean isFraudulent(Transaction transaction);
  String ruleName();
  String failureReason();
}
