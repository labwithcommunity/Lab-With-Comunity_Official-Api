FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar labwithcommunity.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/labwithcommunity.jar"]