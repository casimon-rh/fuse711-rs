FROM registry.access.redhat.com/ubi8/openjdk-11:latest
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn package
CMD ["java","-jar","target/fuse-rest-rh.jar"]