# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files
COPY gradlew .
COPY gradle/ gradle/

# Copy the build configuration files
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src/ src/

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Copy the built JAR file to a known location
RUN cp build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]