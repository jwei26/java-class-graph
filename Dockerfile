FROM openjdk:17-jdk-slim-buster as builder

WORKDIR /app

COPY .mvn /app/.mvn
COPY mvnw /app

RUN chmod +x /app/mvnw

COPY src /app/src
COPY pom.xml /app
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim-buster
COPY --from=builder /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]