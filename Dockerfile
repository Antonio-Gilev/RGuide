FROM openjdk:18-alpine
EXPOSE 8080
COPY RGuide-0.0.1-SNAPSHOT.jar rguide.jar
CMD ["java", "-jar", "rguide.jar"]