# Stage 1: Build
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /build

# Copy only dependency files first (better caching)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd

RUN mvn dependency:go-offline -B

# Copy source last
COPY src src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM amazoncorretto:17-alpine AS app
WORKDIR /app

# Copy and rename jar
COPY --from=build /build/target/*-SNAPSHOT.jar app.jar

# Use a dedicated non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
