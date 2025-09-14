# Stage 1: Build
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine AS build
WORKDIR /build

# Install bash (optional, sometimes needed by mvnw)
RUN apk add --no-cache bash

# Copy only Maven wrapper and pom.xml first to leverage Docker cache
COPY mvnw .
COPY .mvn .mvn
COPY mvnw.cmd mvnw.cmd
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies only (skip tests)
RUN ./mvnw dependency:go-offline -B -DskipTests -q

# Copy source code
COPY src src

# Build the jar without tests and minimal output
RUN ./mvnw clean package -DskipTests -q

# Stage 2: Runtime
FROM --platform=linux/amd64 eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

# Copy only the built jar
COPY --from=build /build/target/*-SNAPSHOT.jar app.jar

# Add non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

# Expose port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
