
FROM eclipse-temurin:25-jdk-alpine
ARG JAR_FILE=target/Pratikum-6-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080