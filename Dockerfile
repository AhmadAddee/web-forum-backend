FROM openjdk:17-jdk-alpine
COPY target/web-spring-container.jar web-spring-container.jar
ENTRYPOINT ["java", "-jar", "web-spring-container.jar"]