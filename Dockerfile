# Etapa 1: Build del .jar dentro del contenedor
FROM maven:3.9.5-eclipse-temurin-22-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final minimizada
FROM eclipse-temurin:22-jdk-alpine
VOLUME /tmp
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=render"]
