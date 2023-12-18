FROM openjdk:17

WORKDIR /app
COPY build/libs/legendfive-0.0.1-SNAPSHOT.jar ./gateway-service.jar
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]

EXPOSE 8000
