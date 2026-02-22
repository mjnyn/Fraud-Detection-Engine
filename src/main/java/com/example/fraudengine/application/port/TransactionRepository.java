package com.example.fraudengine.application.port;

import com.example.fraudengine.domain.model.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.swing.text.html.Option;

public interface TransactionRepository {

  Transaction save(Transaction transaction);
  Optional<Transaction> findById(UUID id);
  Optional<List<Transaction>> findAll();
  void delete(UUID id);

}
