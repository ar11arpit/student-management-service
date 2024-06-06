###########Builder Image############

FROM maven:3.8.3-openjdk-17 AS builder

LABEL maintainer="Arpit Patidar <arpitpatidar28@gmail.com> <+971551208387 / +91551208387>"

ARG TARGETARCH

# image layer
WORKDIR /app

ADD pom.xml /app

# Image layer: with the application
COPY . /app

RUN mvn clean package -DskipTests

###########Final Image############

FROM openjdk:17-alpine

LABEL maintainer="Arpit Patidar <arpitpatidar28@gmail.com> <+971551208387 / +91551208387>"

COPY --from=builder /app/target/*.jar /opt/ms-fee-collection-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/opt/ms-fee-collection-service.jar"]
