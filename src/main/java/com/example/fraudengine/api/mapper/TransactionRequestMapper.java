package com.example.fraudengine.api.mapper;

import com.example.fraudengine.api.TransactionRequest;
import com.example.fraudengine.domain.model.Transaction;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class TransactionRequestMapper {

  public Transaction toDomain(TransactionRequest request) {
    return new Transaction(
      null,
      request.accountId(),
      request.amount(),
      request.currency(),
      request.country(),
      Instant.now()
    );
  }
}
