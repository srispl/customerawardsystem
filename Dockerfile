FROM openjdk:11-jre

EXPOSE 8080

ADD target/customer-rewards-1.0.jar customer-rewards.jar
ENTRYPOINT ["java","-jar","/customer-rewards.jar"]