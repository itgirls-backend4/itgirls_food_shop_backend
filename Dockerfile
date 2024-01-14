#
# Build stage
#
FROM maven:3.9.2-eclipse-temurin-17-alpine as builder
COPY ./src src/
COPY ./pom.xml pom.xml

RUN mvn clean package -DskipTests
#
# Package stage
#
FROM eclipse-temurin:17-jre-alpine
COPY --from=builder target/*.jar Product-0.0.1.jar
EXPOSE 80
CMD ["java","-jar","Product-0.0.1.jar"]