FROM openjdk:17

WORKDIR /app

COPY ./target/bilingsoftware-0.0.1-SNAPSHOT.jar /app

EXPOSE 8000

CMD ["java", "-jar", "bilingsoftware-0.0.1-SNAPSHOT.jar"]
