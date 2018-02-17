FROM openjdk:8-jre

COPY ./target/* /tmp/
COPY configuration.yml /tmp/

WORKDIR /tmp

EXPOSE 8000:8000

CMD java -jar farmer-1.0-SNAPSHOT.jar server configuration.yml
