# Use an official Maven image to build the project
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY . /app

# Build the Maven project (creates a JAR file in the target directory)
RUN mvn clean install

# Use an official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container for the runtime
WORKDIR /app

# Copy the JAR file from the previous build stage
COPY --from=build /app/target/Math-0.0.1-SNAPSHOT.jar Math.jar

# Specify the command to run the Spring Boot application
CMD ["java", "-jar", "Math.jar"]
