FROM maven:3.9.12-eclipse-temurin-21 as builder
WORKDIR /app
COPY pom.xml .
COPY ./billing-service/pom.xml ./billing-service/pom.xml
COPY ./patient-service/pom.xml ./patient-service/pom.xml
COPY ./contract/pom.xml ./contract/pom.xml

RUN mvn -pl contract, billing-service verify --fail-never
COPY ./contract/src ./contract/src
COPY ./billing-service/src ./billing-service/src
RUN mvn -pl billing-service -am package -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.23 as runner
WORKDIR /app
COPY --from=builder ./app/billing-service/target/billing-service-*.jar ./app.jar
EXPOSE 8081
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "app.jar"]

