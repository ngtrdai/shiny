FROM maven:3.8.1-openjdk-11 AS builder

WORKDIR /app

COPY ./identity-webhook/pom.xml .
COPY ./identity-webhook/src ./src

RUN rm -rf ~/.m2/repository  # Optional: Clean local Maven repository
RUN mvn clean package         # Build the Maven project

FROM quay.io/keycloak/keycloak:21.1.2

WORKDIR /opt/keycloak

COPY --from=builder /app/target/identity-webhook-1.0.0.jar /opt/keycloak/providers/identity-webhook-1.0.0.jar

ENTRYPOINT ["./bin/kc.sh", "start-dev"]