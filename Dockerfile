FROM openjdk:17-jdk

COPY target/karriGo-demo.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "karriGo-demo.jar"]