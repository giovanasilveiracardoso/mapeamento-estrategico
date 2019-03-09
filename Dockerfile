FROM maven:3.3-jdk-8 as builder
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn clean install -f /usr/src/mymaven -DskipTests

FROM java:8

COPY --from=builder /usr/src/mymaven/target/mapeamento-estrategico-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]