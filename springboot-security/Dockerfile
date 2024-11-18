# Stage 1: Build stage
FROM maven:3.8.7-openjdk-17 AS build

# Copy Maven configuration files for dependency resolution
COPY pom.xml ./
COPY .mvn .mvn

# Copy the application source code
COPY src src

# Build the project and create the executable JAR, skipping tests
RUN mvn clean install -DskipTests

# Stage 2: Run stage
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /userservice

# Copy the JAR file from the build stage
COPY --from=build target/*.jar userservice.jar

# Expose port 1113 for the application
EXPOSE 1113

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "userservice.jar"]