FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/tlink-server-0.0.1-SNAPSHOT.jar /app/tlink-server.jar
CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "tlink-server.jar"]