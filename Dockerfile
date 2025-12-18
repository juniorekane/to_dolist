FROM eclipse-temurin:21-jre
LABEL authors="junior"

WORKDIR /app

COPY target/app.jar app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]