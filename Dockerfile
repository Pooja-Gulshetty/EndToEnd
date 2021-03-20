FROM openjdk:8
EXPOSE 8080
WORKDIR /
COPY target/EndToEnd-1.0-SNAPSHOT.jar application.jar
CMD java -jar application.jar