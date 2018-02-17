FROM openjdk:8-jre

COPY ./target/* /tmp/
COPY configuration.yml /tmp/

WORKDIR /tmp

EXPOSE 8002

CMD java -jar snackbar-1.0-SNAPSHOT.jar server configuration.yml
