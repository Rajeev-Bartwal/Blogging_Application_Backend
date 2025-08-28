FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

