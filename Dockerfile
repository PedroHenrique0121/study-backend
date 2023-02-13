#FROM maven:3.8.1-openjdk-16-slim As build
#COPY /src /app/crc
#COPY /pom.xml /app
#RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM  openjdk:11
EXPOSE 8080
COPY target/study.jar study.jar
ENTRYPOINT ["java", "-jar", "/study.jar"]
#
#EXPOSE 5885
#EXPOSE 8080
#
#CMD java ${ADDITIONAL_OPTS} - jar app.jar --spring-profiles.active=${PROFILE}
#
#
##ARG  JAR_FILE=target/*.jar
##COPY ${JAR_FILE} app.jar

#ARG PROFILE
#ARG ADDITIONAL_OPTS
#
#ENV PROFILE=${PROFILE}
#ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}
