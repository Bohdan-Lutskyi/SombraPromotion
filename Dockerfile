FROM openjdk:11
ADD target/promotion-0.0.1-SNAPSHOT.jar promotion-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "promotion-0.0.1-SNAPSHOT.jar", "-Dspring.profiles.active=dev"]