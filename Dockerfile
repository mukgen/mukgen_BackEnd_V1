FROM openjdk:19
COPY build/libs/mukgen-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]