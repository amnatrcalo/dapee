FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11
COPY --from=MAVEN_BUILD target/*.jar app.jar
EXPOSE 8087
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]
