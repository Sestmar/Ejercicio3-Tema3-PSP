# Etapa 1: Build del .jar dentro del contenedor
FROM maven:3.9.6-eclipse-temurin-22 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final minimizada para ejecutar el jar
FROM eclipse-temurin:22-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/cursos-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=render"]