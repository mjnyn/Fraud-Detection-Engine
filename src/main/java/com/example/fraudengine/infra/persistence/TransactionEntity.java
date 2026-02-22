package com.example.fraudengine.infra.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class TransactionEntity {

  @Id
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private String accountId;

  @Column(nullable = false, precision = 8, scale = 2)
  private BigDecimal amount;

  @Column(nullable = false, length = 3)
  private String currency;

  @Column(nullable = false)
  private String country;

  @Column(nullable = false)
  private Instant occuredAt;

  protected TransactionEntity() {}

  public UUID getId() {
    return id;
  }

  public String getAccountId() {
    return accountId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public String getCountry() {
    return country;
  }

  public Instant getOccuredAt() {
    return occuredAt;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setOccuredAt(Instant occuredAt) {
    this.occuredAt = occuredAt;
  }
}
