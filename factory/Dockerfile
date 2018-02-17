FROM openjdk:8-jre

COPY ./target/* /tmp/
COPY configuration.yml /tmp/

WORKDIR /tmp

EXPOSE 8001

CMD java -jar factory-1.0-SNAPSHOT.jar server configuration.yml
