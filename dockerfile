FROM openjdk:8-jdk-alpine
COPY target/*.jar KafkaProducer.jar
ENTRYPOINT ["java","-jar","/KafkaProducer.jar"]