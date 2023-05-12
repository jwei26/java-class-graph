FROM openjdk:17-slim

WORKDIR /app

COPY .mvn /app/.mvn
COPY mvnw /app

COPY src /app/src
COPY pom.xml /app

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-slim
COPY --from=0 /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
