package com.example.fraudengine.infra.persistence;

import com.example.fraudengine.domain.model.Transaction;

public class TransactionEntityMapper {

  private TransactionEntityMapper() {}

  public static TransactionEntity toEntity(Transaction transaction) {
    if (transaction == null) {
      return null;
    }

    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setId(transaction.getId());
    transactionEntity.setAmount(transaction.getAmount());
    transactionEntity.setCurrency(transaction.getCurrency());
    transactionEntity.setAccountId(transaction.getAccountId());
    transactionEntity.setCountry(transaction.getCountry());
    transactionEntity.setOccuredAt(transaction.getOccuredAt());
    return transactionEntity;
  }

  public static Transaction toDomain(TransactionEntity transactionEntity) {
    if (transactionEntity == null) {
      return null;
    }
    return new Transaction(
      transactionEntity.getId(),
      transactionEntity.getAccountId(),
      transactionEntity.getAmount(),
      transactionEntity.getCurrency(),
      transactionEntity.getCountry(),
      transactionEntity.getOccuredAt()
    );
  }
}
