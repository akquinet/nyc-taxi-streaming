FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
CMD  /bin/sh -c "echo 'Boot up Delay' && sleep 60 && java -jar /app.jar"
