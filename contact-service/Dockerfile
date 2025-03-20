FROM openjdk:11-jre
WORKDIR /usr/app/
COPY target/contactapp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9000
ENV MONGO_HOST=mongo_db
ENV MONGO_DB=contactsdb
ENV MONGO_PORT=27017
ENTRYPOINT ["java", "-jar", "app.jar"]