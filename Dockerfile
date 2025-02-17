# Use OpenJDK 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR file into the container
COPY target/AoristHomes-0.0.1-SNAPSHOT.jar app.jar

# Copy keys.properties file into container
COPY src/main/resources/keys.properties /app/config/keys.properties

# Set environment to read the properties file
ENV SPRING_CONFIG_IMPORT=optional:file:/app/config/keys.properties

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
