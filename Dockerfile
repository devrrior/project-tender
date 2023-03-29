FROM amazoncorretto:11.0.17
ADD target/project-tender.jar project-tender.jar
ENTRYPOINT ["java", "-jar", "app.jar"]