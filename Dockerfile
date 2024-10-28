# Phase 1: Build the application
FROM openjdk:17-jdk-slim AS build
WORKDIR /app
COPY . .  
RUN mvn clean package -DskipTests

# Phase 2: Create the production image
FROM maven:3.9.8-eclipse-temurin-21
WORKDIR /app
# Adjust the filename based on your actual artifact ID and version
COPY --from=build /app/target/Math-1.0-SNAPSHOT.jar ./math.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "math.jar"]
