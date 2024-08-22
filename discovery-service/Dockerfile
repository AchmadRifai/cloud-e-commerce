# Maven
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean -e -B package -DskipTests=true
# RTSDK Java
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/discovery-service-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "./discovery-service-0.0.1-SNAPSHOT.jar"]