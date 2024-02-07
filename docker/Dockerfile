FROM openjdk:17-jdk-alpine as builder

ENV APP_HOME=/app/
WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle

RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew build

FROM openjdk:17-jdk-alpine

# Install OpenCV
#RUN apk add --no-cache opencv-dev
RUN apk update && \
    apk upgrade && \
    apk add --no-cache apache-ant \
    apk add --no-cache bash git openssh && \
    apk add --no-cache --virtual .build-deps build-base cmake linux-headers && \
    apk add --no-cache libjpeg-turbo-dev libpng-dev libwebp-dev tiff-dev libzip-dev libcurl && \
    apk add --no-cache python3 python3-dev py3-pip && \
    apk add --no-cache ffmpeg-dev ffmpeg-libs && \
    apk add --no-cache opencv opencv-dev && \
    ln -s /usr/include/locale.h /usr/include/xlocale.h && \
    rm -rf /var/cache/apk/*

ENV APP_HOME=/app/
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs /app



EXPOSE 8080
CMD ["java","-jar","/app/resizer.jar"]