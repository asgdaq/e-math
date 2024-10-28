# Faza 1: Construirea aplicației
FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY . .  
RUN mvn clean package -DskipTests

# Faza 2: Crearea imaginii de producție
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /math.jar ./math.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "math.jar"]
