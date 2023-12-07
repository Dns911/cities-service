#ARG BUILD_IMAGE=public.ecr.aws/docker/library/maven:3.8-amazoncorretto-17
#ARG RUNTIME_IMAGE=public.ecr.aws/amazoncorretto/amazoncorretto:17-al2-full
#
#### STEP 1 build executable binary
#FROM ${BUILD_IMAGE} as builder
#ARG TOKEN
#
## PREPARE SRC TO BUILD
#COPY settings-ci.xml pom.xml /tmp/
#COPY src /tmp/src/
#WORKDIR /tmp/
#
## BUILD
#RUN CODEARTIFACT_AUTH_TOKEN=$TOKEN mvn -s settings-ci.xml package
#
### STEP 2 build final image (small image)
#FROM ${RUNTIME_IMAGE}
#COPY --from=builder /tmp/target/*.jar /app/application.jar
#
#EXPOSE 8080
#ENTRYPOINT [ "java", "-Xmx512m", "-Xms512m", "-jar", "/app/application.jar" ]

FROM gradle:8.2.1-jdk17 as builder

WORKDIR /temp

COPY ./src src
COPY ./build.gradle build.gradle
COPY ./settings.gradle settings.gradle

RUN gradle clean build


FROM openjdk:17.0.2-jdk-slim-buster

WORKDIR /app

COPY --from=builder temp/build/libs/location-service-0.0.1.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
