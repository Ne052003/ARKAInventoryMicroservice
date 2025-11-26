FROM gradle:8.10.2-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app
COPY --from=build app/application/build/libs/*.jar app.jar

EXPOSE 8088

ENTRYPOINT ["java","-jar","app.jar"]