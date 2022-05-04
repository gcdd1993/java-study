#!/usr/bin/env bash

# jodconverter
VERSION=$(git rev-parse --short HEAD)
docker build -f Dockerfile \
    -t jodconverter:latest-snapshot \
    --build-arg MODULE=jodconverter \
    .