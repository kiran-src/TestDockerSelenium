FROM maven:3.9.6-eclipse-temurin-21-alpine

WORKDIR /workspace/karate-demo
COPY src ./src
COPY pom.xml .

RUN mvn clean install -DskipTests=true

CMD ["mvn", "test"]