FROM openjdk:8u181-jre-alpine3.8

ENV APP_NAME=gateway
ENV APP_HOME_DIR=/opt/app/$APP_NAME

RUN mkdir -p $APP_HOME_DIR/lib

ADD target/$APP_NAME.jar $APP_HOME_DIR/lib/app.jar
WORKDIR $APP_HOME_DIR/lib

ENTRYPOINT ["java","-jar", "app.jar"]