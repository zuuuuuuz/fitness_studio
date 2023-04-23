FROM eclipse-temurin:17-jre-focal
MAINTAINER BulDima

ADD ./target/user-service-0.1.0.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/user-service-0.1.0.jar"]

EXPOSE 8080 8089