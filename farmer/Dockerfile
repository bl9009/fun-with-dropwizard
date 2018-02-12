FROM openjdk:8-jre

RUN apt-get update
RUN apt-get install -y net-tools
RUN /sbin/ifconfig

COPY ./target/* /tmp/
COPY configuration.yml /tmp/

WORKDIR /tmp

EXPOSE 8000

CMD java -jar farmer-1.0-SNAPSHOT.jar server configuration.yml
