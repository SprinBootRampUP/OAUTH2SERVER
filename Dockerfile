FROM openjdk:17-jdk-slim
VOLUME /tmp
ARG JAR_FILE=target/OAUTH2.0SERVER-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]