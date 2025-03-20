FROM openjdk:11-jre
WORKDIR /usr/app/
COPY target/userservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9005
ENV MYSQL_HOST=mysql_db
ENV MYSQL_DATABASE=contactsdb
ENV MYSQL_PORT=3306
ENV MYSQL_USERNAME=root
ENV MYSQL_PASSWORD=root
ENTRYPOINT ["java", "-jar", "app.jar"]