# Use a base image with Java 8 installed
FROM adoptopenjdk:8-jdk-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY build/libs/*.jar app.jar

# Expose the port your application is running on
EXPOSE 8080

# Set the entry point command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]