FROM openjdk:17-jre-slim

LABEL maintainer="ranyanxia@gmail.com"

EXPOSE 8080
ARG JAR_FILE=target/ddd-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} ddd-service.jar

# Run the jar file
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/ddd-service.jar" ]