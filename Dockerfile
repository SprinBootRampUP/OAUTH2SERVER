

FROM openjdk:17-jdk-slim
WORKDIR /sharan
COPY target/OAUTH2.0SERVER-1.0-SNAPSHOT.jar jarfolder/api.jar
EXPOSE 3006
ENTRYPOINT ["java", "-jar", "jarfolder/api.jar"]
