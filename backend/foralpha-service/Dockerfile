FROM openjdk:17

WORKDIR /app
COPY build/libs/demo-0.0.1-SNAPSHOT.jar ./foralpha-service.jar
ENTRYPOINT ["java", "-jar", "foralpha-service.jar"]
EXPOSE 8002