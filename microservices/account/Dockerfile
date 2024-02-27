FROM openjdk:17-jdk-slim
LABEL authors="nisargpatel"

#Information around who maintains the image
MAINTAINER nmp.com

# Add the application's jar to the image
COPY microservices/account/target/account.jar account.jar

# execute the application
ENTRYPOINT ["java", "-jar", "account.jar"]