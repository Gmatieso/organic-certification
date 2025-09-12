# Stage 1: Build
FROM maven:3.9.6-amazoncorretto-17 AS build
WORKDIR /build

# Copy only dependency files first (better caching)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd

RUN ./mvnw dependency:go-offline -B

# Copy source last
COPY src src
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM amazoncorretto:17-alpine AS app
WORKDIR /app

# Copy and rename jar
COPY --from=build /build/target/*-SNAPSHOT.jar app.jar

# Use a dedicated non-root user (Alpine style)
RUN addgroup -g 1000 spring \
    && adduser -u 1000 -G spring -s /bin/sh -D spring
USER spring:spring

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
