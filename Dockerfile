# Stage 1: Build
FROM --platform=linux/amd64 eclipse-temurin:17-jdk-alpine AS build
WORKDIR /build

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw mvnw
COPY mvnw.cmd mvnw.cmd

# Give executable permission to mvnw
RUN chmod +x mvnw

RUN #./mvnw dependency:go-offline -B

COPY src src
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM --platform=linux/amd64 eclipse-temurin:17-jre-alpine AS app
WORKDIR /app

COPY --from=build /build/target/*-SNAPSHOT.jar app.jar

# Add non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
