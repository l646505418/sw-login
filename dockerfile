FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
ADD target/login-0.0.1-SNAPSHOT.jar /app/app.jar
COPY /configMap/prod/application.yaml /app/resources/application.yaml
EXPOSE 9001
ENTRYPOINT ["java","-jar","-Dconfig.dir=/app/resources","/app/app.jar"]