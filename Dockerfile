# Phase 1: Build the application
FROM openjdk:17-jdk-slim AS build

# Install Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app
COPY . .  
RUN mvn clean package -DskipTests

# Phase 2: Create the production image
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/Math-1.0-SNAPSHOT.jar ./math.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "math.jar"]
