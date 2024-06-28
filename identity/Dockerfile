FROM maven:3.8.1-openjdk-11 as builder

WORKDIR /app

COPY ./identity-webhook/pom.xml .
COPY ./identity-webhook/src ./src

RUN rm -rf ~/.m2/repository
RUN mvn clean package

FROM quay.io/keycloak/keycloak:21.1.2

WORKDIR /opt/keycloak

COPY --from=builder /app/target/identity-webhook-1.0.0.jar /opt/keycloak/providers/identity-webhook-1.0.0.jar

ENTRYPOINT ["./bin/kc.sh", "start-dev"]