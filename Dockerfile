FROM maven:3.3-jdk-8 as builder
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean install -f /usr/src/mymaven
COPY ~/usr/src/mymaven/target/mapeamento-estrategico-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

