# Step 1: Use a base image that has Java installed
FROM openjdk:17-jdk-alpine

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file into the container
COPY target/app.jar /app/app.jar

# Step 4: Expose the port your Spring Boot app runs on (e.g., 8080)
EXPOSE 8080

# Step 5: Run the application when the container starts
CMD ["java", "-jar", "app.jar"]
