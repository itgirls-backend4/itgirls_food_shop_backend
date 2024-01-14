#
# Build stage
#
FROM maven:3.8.2-jdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/Product-0.0.1-SNAPSHOT.jar Product-0.0.1.jar
# ENV PORT=80
EXPOSE 80
ENTRYPOINT ["java","-jar","Product-0.0.1.jar"]