FROM openjdk:17

WORKDIR /app
COPY build/libs/legendfive-0.0.1-SNAPSHOT.jar ./discovery-service.jar
ENTRYPOINT ["java", "-jar", "discovery-service.jar"]

EXPOSE 8761