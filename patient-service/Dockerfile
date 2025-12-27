FROM maven:3.9.12-eclipse-temurin-21 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN --mount=type=cache,target=~/.m2 mvn dependency:go-offline -B
RUN --mount=type=cache,target=~/.m2 mvn package -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.23 as runner
WORKDIR /app
COPY --from=builder ./app/target/patient-service-*.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

