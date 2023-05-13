# 使用官方的OpenJDK 17基础镜像
FROM openjdk:17-jdk-slim-buster as builder

WORKDIR /app

COPY .mvn /app/.mvn
COPY mvnw /app

COPY src /app/src
COPY pom.xml /app

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-slim-buster
COPY --from=builder /app/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
