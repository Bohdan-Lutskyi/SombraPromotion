FROM openjdk:11
ADD target/promotion-0.0.1-SNAPSHOT.jar promotion-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "promotion-0.0.1-SNAPSHOT.jar"]