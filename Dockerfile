FROM eclipse-temurin
WORKDIR /home/rest
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/home/rest/app.jar"]
