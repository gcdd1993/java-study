# Second stage: minimal runtime environment
FROM adoptopenjdk/openjdk11-openj9:alpine-slim

ARG MODULE

ENV TZ=Asia/Shanghai

LABEL name=$MODULE

RUN set -ex \
    && sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories \
    && apk update \
    && apk upgrade \
    && apk add --no-cache tzdata freetype-dev ttf-dejavu fontconfig libreoffice \
    && rm -rf /tmp/* \
    && cp /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone

# copy arthas
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas
# replace default setup with a one disabling logos by default
ADD sofficerc /etc/sofficerc
ADD iniciar.sh /usr/bin/iniciar.sh

WORKDIR /app

COPY build/docker/libs/*.jar /app/lib/
COPY build/docker/$MODULE /app/bin/run
COPY build/docker/$MODULE-*.jar /app/lib/
COPY fonts/* /usr/share/fonts/

# ports and volumes
EXPOSE 8080

RUN chmod +x /usr/bin/iniciar.sh
ENTRYPOINT ["/usr/bin/iniciar.sh"]
