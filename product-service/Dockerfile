FROM eclipse-temurin:17-jre-focal
MAINTAINER BulDima

ADD ./target/product-service-0.1.0.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/product-service-0.1.0.jar"]

EXPOSE 8080 8089