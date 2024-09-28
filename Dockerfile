FROM openjdk:17-jdk-slim
COPY target/labwithcommunity-0.0.1-SNAPSHOT.jar /app/labwithcommunity.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/labwithcommunity.jar"]