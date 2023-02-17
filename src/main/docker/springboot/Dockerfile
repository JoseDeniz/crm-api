FROM openjdk:17-jdk-slim
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar

ENV SPRING_DATASOURCE_USERNAME=admin1
ENV SPRING_DATASOURCE_PASSWORD=admin1

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "application.jar"]
EXPOSE 8080