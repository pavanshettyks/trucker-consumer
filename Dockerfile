FROM maven:3.6.3-jdk-8
MAINTAINER Pavan

COPY . /usr/src/app/
WORKDIR /usr/src/app/

RUN  mvn -f /usr/src/app/pom.xml clean install
RUN mv target/TruckerConsmer-0.0.1-SNAPSHOT.jar TruckerConsmer.jar

ENTRYPOINT java -jar TruckerConsmer.jar