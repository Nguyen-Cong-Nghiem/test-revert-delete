
FROM maven:3.5.3-jdk-10 as builder
# Install project dependencies and keep sources
# make source folder
WORKDIR /app

# install maven dependency packages (keep in image)
COPY . /app
RUN mvn clean
RUN mvn compile package

FROM openjdk:11.0.2-jre-slim
RUN mkdir -p /app
RUN mkdir -p /app/conf
RUN mkdir -p /app/src/main/resources/i18n

WORKDIR /app

COPY --from=builder /app/target /app
COPY --from=builder /app/conf /app/conf
COPY --from=builder /app/src/main/resources/i18n /app/src/main/resources/i18n

CMD java -cp ptsp.test-1.0-SNAPSHOT-jar-with-dependencies.jar vn.shippo.rider.Application

