FROM adoptopenjdk/openjdk11
COPY /build/libs/FCA-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "FCA-0.0.1-SNAPSHOT.jar"]
