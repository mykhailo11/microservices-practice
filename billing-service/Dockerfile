FROM maven:3.9.12-eclipse-temurin-21 as builder
WORKDIR /app
COPY . .

RUN --mount=type=cache,target=/root/.m2 mvn -B dependency:go-offline
RUN --mount=type=cache,target=/root/.m2 mvn -pl billing-service -am package -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.23 as runner
WORKDIR /app
COPY --from=builder ./app/billing-service/target/billing-service-*.jar ./app.jar
EXPOSE 8081
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app.jar"]

