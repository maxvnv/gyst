FROM openjdk:8

MAINTAINER Max Ivanov maxfordevelopment@gmail.com

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=./target/gyst-1.0-SNAPSHOT.jar

ADD ${JAR_FILE} gyst.jar

# ENV JAVA-OPTS="-Dspring.profiles.active=local"

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=local","-jar","/gyst.jar"]