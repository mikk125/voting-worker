FROM openjdk:11
COPY build/libs/voting-worker-0.0.1-SNAPSHOT.jar voting-message-consumer.jar
ENTRYPOINT ["java","-jar","voting-message-consumer.jar"]