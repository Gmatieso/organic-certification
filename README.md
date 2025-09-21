## AgriTrack Backend
AgriTrack is a backendSystem designed to streamline agricultural data management, certification, and tracking. The goal is to provide a scalable and secure foundation for applications in the agri-tech domain
enabling farmers, cooperatives, and regulators to interact seamlessly.

## 🏗️ Architecture 
AgriTrack follows a  layered architecture based on Spring Boot's Service-Repository-Controller pattern.

## Controller Layer
- This will Exposes our **RESTFUL APIs** for frontend and third-party integrations.
- It will also Handles all network request/response
## Service Layer
- This will Encapsulates our b/s logic and domain rules.
- It will also Manages workflow such as certification approval in future todos
## DTO Layer
- Here i hv used **Java Records** to create immutable, Data Transfer Objects. 
- Just to Separate API payloads from internal domain models. 
## Mapper Layer
- Here i hv used **MapStruck** for automatic conversion btwn Entities and DTOs.
- Just to Reduce boilerplate code and ensure consistent transformations. 
## Repository Layer
- This is my Data Persistence layer that uses **Spring Data JPA**. 
- This manages transactions and database queries against PostgreSQL.
## Database Layer
- For our PostgreSQL i settled on Flyway for migration and schema for versioning.

## Deployment & DevOps
- Here i hv Containerized with Docker for consistency across environments. for indepth guiding on how to set this up kindly navigate to `docs` folder onto the `db` folder we hv more guidelines for setting up the environment.
  - Configurable environment via `application.yaml`,  `compose.yaml`, and `Dockerfile`.
  - Get your deployed backend url here: `https://organic-certification-production.up.railway.app`

## Tech Stack 
- Backend Framework: Java 17 + SpringBoot 
- Database: PostgreSQL 17
- Database Migrations: Flyway
- API Testing: Postman and Swagger  : checks docs - local-database for more insights
- Build Tool: Maven  -- `mvn clean compile`  `mvn clean package -DskipTests` 
- CI/CD: Railways automatic CICD ... Github Actions for Backup
- Cloud Deployment Ready: Railway .... AWS EC2 for Backup .. 

#### <br>👷‍♂️ WORK IN PROGRESS 👩‍🏭</br>
![Work in Progress](https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExOGp4eW9zbWdzNGdwcGNuYW4ybWszNnBodDVpNmt5bHNsZ2MzdWp3MCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/mCVBZCxLNXvakHj1cN/giphy.gif)

### Get your Publish API Documentation here:
https://documenter.getpostman.com/view/21219256/2sB3HtEbfV

