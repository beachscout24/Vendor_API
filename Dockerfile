FROM openjdk:17

WORKDIR /app

COPY ./target/vendor-api-0.0.1-SNAPSHOT.jar /app

EXPOSE 8081

CMD ["java", "-jar", "vendor-api-0.0.1-SNAPSHOT.jar"]