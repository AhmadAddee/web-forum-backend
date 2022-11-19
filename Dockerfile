#FROM openjdk:17-jdk-alpine
#ADD target/*.jar app.jar
FROM openjdk:17-jdk-alpine
COPY target/web-forum-0.0.1-SNAPSHOT.jar web-forum-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/web-forum-0.0.1-SNAPSHOT.jar"]