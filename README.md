# Fraud Rule Engine

A system that processes categorized transaction events and flags potential fraud. Applies a set of fraud rules per transaction based on different criteria and then store them in a data store.

## Run locally (requires Java 17)

```bash
./mvnw clean package
./mvnw spring-boot:run
```

## Run via Docker

```bash
docker compose up --build
```

## Sample Input
```
curl -X POST "http://localhost:8080/api/v1/fraud/evaluate" \
-H "Content-Type: application/json" \
-d '{
"accountId": "act123",
"amount": 100.00,
"currency": "ZAR",
"country": "ZA"
}'
```

The app listens on `http://localhost:8080`.