

FROM gradle:8.5-jdk17 as builder

WORKDIR /temp

COPY ./src src
COPY ./build.gradle build.gradle
COPY ./settings.gradle settings.gradle

RUN gradle clean build


FROM openjdk:17.0.2-jdk-slim-buster

WORKDIR /app

COPY --from=builder temp/build/libs/cities-service-0.0.1.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
