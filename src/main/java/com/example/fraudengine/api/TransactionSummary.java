package com.example.fraudengine.api;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record TransactionSummary (
  UUID id,
  String accountId,
  BigDecimal amount,
  String currency,
  String country,
  Instant occuredAt
) {}