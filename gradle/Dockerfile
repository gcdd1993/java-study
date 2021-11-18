# Second stage: minimal runtime environment
FROM registry.cn-shanghai.aliyuncs.com/halmawork/openjdk11-openj9:jre

ARG MODULE

ENV TZ=Asia/Shanghai

LABEL name=$MODULE

RUN ln -fs /usr/share/zoneinfo/$TZ /etc/localtime \
    && dpkg-reconfigure -f noninteractive tzdata

WORKDIR /app

COPY $MODULE/build/docker/libs/*.jar /app/lib/
COPY $MODULE/build/docker/$MODULE /app/bin/run
COPY $MODULE/build/docker/$MODULE-*.jar /app/lib/

EXPOSE 8080

CMD ["/app/bin/run"]