FROM openjdk:21
ARG JAR_FILE="/MIS PROYECTOS/JAVA/spring-app/target/app.jar"
COPY ${JAR_FILE} app.jar
EXPOSE 8080
CMD [ "java", "-jar", "app.jar" ]