ARG PROJECT_NAME=billing-service

FROM maven:3.9.12-eclipse-temurin-21 as builder
ARG PROJECT_NAME
WORKDIR /app
COPY . .

RUN --mount=type=cache,target=/root/.m2 mvn -B dependency:go-offline
RUN --mount=type=cache,target=/root/.m2 mvn -pl $PROJECT_NAME -am package -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.23 as runner
ARG PROJECT_NAME
WORKDIR /app
COPY --from=builder ./app/$PROJECT_NAME/target/$PROJECT_NAME-*.jar ./app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]