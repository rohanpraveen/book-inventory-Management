FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the entire project
COPY . .

# Ensure mvnw is executable (if it exists)
RUN chmod +x mvnw

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","target/*.jar"]