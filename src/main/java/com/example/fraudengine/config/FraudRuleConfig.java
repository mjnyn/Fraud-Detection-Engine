package com.example.fraudengine.config;

import com.example.fraudengine.domain.rule.FraudRule;
import com.example.fraudengine.domain.rule.HighAmount;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FraudRuleConfig {

  @Bean
  public HighAmount highAmountRule() {
    return new HighAmount(BigDecimal.valueOf(1000));
  }

  @Bean
  public List<FraudRule> fraudRules(HighAmount highAmountRule) {
    return List.of(highAmountRule);
  }
}