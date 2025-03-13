FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=builder /app/target/guestbook-0.0.1-SNAPSHOT.jar /app/guestbook.jar

EXPOSE 3000

CMD ["java", "-jar", "guestbook.jar"]