FROM gradle:8.2.1 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:17-jdk-slim
EXPOSE 4490
COPY --from=build /home/gradle/src/build/libs/fizzbuzz-rest-api-0.0.1-SNAPSHOT.jar /app/fizzbuzz-rest-api.jar
RUN bash -c 'touch /app/fizzbuzz-rest-api.jar'
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/fizzbuzz-rest-api.jar"]

