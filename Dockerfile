FROM openjdk:17
RUN mkdir /opt/app
COPY deal-srv/target/deal-srv-*.jar /opt/app/deal.jar
ENV THE_APP_JAR opt/app/deal.jar
CMD ["sh", "-c", "java -jar $THE_APP_JAR"]
LABEL "project"="deal"