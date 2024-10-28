FROM gradle:jdk21 AS build

WORKDIR /app

COPY . .

RUN gradle build

FROM openjdk:21-jdk-slim

WORKDIR /opt/app

COPY --from=build /app/build/libs/useless-facts-all.jar .

EXPOSE 8080

CMD ["java", "-jar", "useless-facts-all.jar"]