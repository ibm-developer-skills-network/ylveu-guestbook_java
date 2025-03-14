# Please choose from the below set of Docker commands to complete your Dockerfile:

# COPY
# RUN
# ADD
# EXPOSE
# FROM
# CMD
# PUSH
# PULL
# WORKDIR

FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app


<Please fill in the required Docker command> --from=builder /app/target/guestbook-0.0.1-SNAPSHOT.jar /app/guestbook.jar

<Please fill in the required Docker command> 3000

CMD ["java", "-jar", "guestbook.jar"]