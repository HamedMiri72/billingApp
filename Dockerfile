FROM openjdk:17

WORKDIR /app

COPY ./target/billingsoftware-0.0.1-SNAPSHOT.jar /app

EXPOSE 8000

CMD ["java", "-jar", "billingsoftware-0.0.1-SNAPSHOT.jar"]
