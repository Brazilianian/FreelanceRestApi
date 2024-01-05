FROM openjdk:19-jdk-alpine3.16 as build_stage
WORKDIR /home/rest

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw package -DskipTests

FROM openjdk:19-jdk-alpine3.16 as run_stage
COPY --from=build_stage /home/rest/target/*.jar ./FreelanceRestApi.jar
CMD ["java", "-jar", "FreelanceRestApi.jar"]
