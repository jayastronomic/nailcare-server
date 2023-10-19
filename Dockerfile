FROM openjdk:17
COPY target/nailcare.jar nailcare.jar
ENTRYPOINT ["java", "-jar", "/nailcare.jar"]