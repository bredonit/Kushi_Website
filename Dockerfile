# First stage: Build the Spring Boot application
FROM eclipse-temurin:17-jdk AS build

# Set the working directory inside the container
WORKDIR /app

# Install required dependencies
RUN apt-get update && apt-get install -y unzip

# Copy only the Maven wrapper and POM file for dependency caching
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Download dependencies first (cached)
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy the entire project into the container
COPY . .

# Build the application inside the container
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Debugging: Check if the WAR file exists
RUN ls -lah target/

# Second stage: Create a lightweight runtime container
FROM eclipse-temurin:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Ensure the target directory exists
RUN mkdir -p /app/target

# Copy the built WAR file from the first stage
COPY --from=build /app/target/*.war app.war

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.war"]
