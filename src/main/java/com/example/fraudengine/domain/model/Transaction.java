package com.example.fraudengine.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Transaction {

  private UUID id;
  private String accountId;
  private BigDecimal amount;
  private String currency;
  private String country;
  private Instant occuredAt;

  public Transaction(UUID id, String accountId, BigDecimal amount, String currency, String country, Instant occuredAt) {
    this.id = id;
    this.accountId = Objects.requireNonNull(accountId, "accountId cannot be null");
    this.amount = Objects.requireNonNull(amount, "amount cannot be null");
    this.currency = Objects.requireNonNull(currency, "currency cannot be null");
    this.country = Objects.requireNonNull(country, "country cannot be null");
    this.occuredAt = occuredAt;
  }

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
