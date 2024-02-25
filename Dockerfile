FROM openjdk:11
ADD ./target/*.jar /usr/src/demo-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
EXPOSE 8080
ENTRYPOINT ["java","-jar", "demo-0.0.1-SNAPSHOT.jar"]