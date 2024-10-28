FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar Math.jar
ENTRYPOINT ["java", "-jar", "Math.jar"]
