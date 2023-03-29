FROM amazoncorretto:17-alpine3.14
ADD target/project-tender.jar project-tender.jar
ENTRYPOINT ["java", "-jar", "project-tender.jar"]