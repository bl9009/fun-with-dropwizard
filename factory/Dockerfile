FROM openjdk:8-jre

RUN apt-get update
RUN apt-get install -y net-tools
RUN /sbin/ifconfig

COPY ./target/* /tmp/
COPY src/main/resources/configuration.yml /tmp/

WORKDIR /tmp

EXPOSE 8001

CMD java -jar factory-1.0-SNAPSHOT.jar server configuration.yml
