FROM openjdk:11-jdk
EXPOSE 8008
ARG JAR_FILE=build/libs/*.jar
ENV MYSQL_URL = $MYSQL_URL
ENV JWT_SECRET_KEY = $JWT_SECRET_KEY
ENV REDIS_HOST = $REDIS_HOST
ENV REDIS_PW = $REDIS_PW
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.datasource.url=${MYSQL_URL}", "-Dauth.jwt.secret=${JWT_SECRET_KEY}", "-Dspring.redis.host=${REDIS_HOST}", "-Dspring.redis.password=${REDIS_PW}", "-jar","/app.jar"]