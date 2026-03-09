# Use OpenJDK 17
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY target/AoristHomes-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]