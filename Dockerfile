FROM openjdk:17-jdk

EXPOSE 8080

ADD target/karrigo-new-image.jar karrigo-new-image.jar


ENTRYPOINT ["java", "-jar", "karrigo-new-image.jar"]