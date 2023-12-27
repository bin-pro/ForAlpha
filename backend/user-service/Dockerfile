FROM openjdk:17

WORKDIR /app
COPY build/libs/legendfive-0.0.1-SNAPSHOT.jar ./user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]
EXPOSE 8001