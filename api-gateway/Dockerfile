# Maven
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN sed -i 's/localhost/host.docker.internal/g' ./src/main/resources/application.yml
RUN mvn clean -e -B package -DskipTests=true
# RTSDK Java
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/api-gateway-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "./api-gateway-0.0.1-SNAPSHOT.jar"]