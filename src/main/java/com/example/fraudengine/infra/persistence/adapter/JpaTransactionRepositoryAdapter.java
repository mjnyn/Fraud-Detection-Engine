package com.example.fraudengine.infra.persistence.adapter;

import com.example.fraudengine.application.port.TransactionRepository;
import com.example.fraudengine.domain.model.Transaction;
import com.example.fraudengine.infra.persistence.JpaTransactionRepository;
import com.example.fraudengine.infra.persistence.TransactionEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import com.example.fraudengine.infra.persistence.TransactionEntityMapper;

@Repository
public class JpaTransactionRepositoryAdapter implements TransactionRepository {

  private final JpaTransactionRepository jpaTransactionRepository;

  public JpaTransactionRepositoryAdapter(JpaTransactionRepository jpaTransactionRepository) {
    this.jpaTransactionRepository = jpaTransactionRepository;
  }

  @Override
  @Transactional
  public Transaction save(Transaction transaction) {
    TransactionEntity entity = TransactionEntityMapper.toEntity(transaction);
    TransactionEntity saved = jpaTransactionRepository.save(entity);
    return TransactionEntityMapper.toDomain(saved);
  }

  @Override
  public Optional<Transaction> findById(UUID id) {
    return jpaTransactionRepository.findById(id).map(TransactionEntityMapper::toDomain);
  }

  @Override
  public Optional<List<Transaction>> findAll() {
    List<Transaction> transactions = jpaTransactionRepository.findAll().stream().map(TransactionEntityMapper::toDomain).collect(Collectors.toList());
    return transactions.isEmpty() ? Optional.empty() : Optional.of(transactions);
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    jpaTransactionRepository.deleteById(id);
  }
}
