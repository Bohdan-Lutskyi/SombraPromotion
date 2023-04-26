FROM openjdk:11

COPY target/promotion-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "application.jar"]