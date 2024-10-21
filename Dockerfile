FROM openjdk:21
ARG JAR_FILE=/spring-app/*.jar
COPY ${JAR_FILE} app.jar
CMD [ "java", "-jar", "app.jar" ]