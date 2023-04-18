FROM openjdk:11
#ADD target/promotion-0.0.1-SNAPSHOT.jar promotion-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java", "-jar", "promotion-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "promotion-0.0.1-SNAPSHOT.jar"]

COPY target/promotion-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]