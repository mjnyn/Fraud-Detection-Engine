package com.example.fraudengine.api;

import java.math.BigDecimal;

public record TransactionRequest (
  String accountId,
  BigDecimal amount,
  String currency,
  String country
) {}
