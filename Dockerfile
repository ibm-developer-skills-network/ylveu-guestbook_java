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

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the pre-built jar file (you'll need to build this first)
<Please fill in the required Docker command>  target/guestbook-0.0.1-SNAPSHOT.jar app.jar

<Please fill in the required Docker command>  3000

CMD ["java", "-jar", "app.jar"]