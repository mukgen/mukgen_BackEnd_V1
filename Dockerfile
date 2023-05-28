FROM openjdk:19
COPY build/libs/mukgen-*.jar app.jar
COPY build/libs/mukgen-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]