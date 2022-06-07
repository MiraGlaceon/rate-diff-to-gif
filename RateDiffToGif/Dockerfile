FROM openjdk
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} RateDiffToGif.jar
ENTRYPOINT ["java","-jar","RateDiffToGif.jar"]