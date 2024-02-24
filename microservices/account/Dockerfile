FROM openjdk:17-jdk-slim
LABEL authors="nisargpatel"

#Information around who maintains the image
MAINTAINER nmp.com

# Add the application's jar to the image
COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "account-0.0.1-SNAPSHOT.jar"]