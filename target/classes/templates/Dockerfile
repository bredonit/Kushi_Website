FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy all project files
COPY . .

# Grant execute permissions to Maven wrapper
RUN chmod +x mvnw 

# Build the application
RUN ./mvnw clean package

# Run the application
CMD ["java", "-jar", "target/*.jar"]
