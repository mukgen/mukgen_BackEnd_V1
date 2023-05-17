FROM openjdk:17-jre
COPY build/libs/mukgen-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]