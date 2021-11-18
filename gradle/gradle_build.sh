#! /bin/bash

echo "org.gradle.daemon=false" >>~/.gradle/gradle.properties &&
  echo "org.gradle.parallel=true" >>~/.gradle/gradle.properties &&
  echo "maven_username=$MAVEN_USERNAME" >>~/.gradle/gradle.properties &&
  echo "maven_password=$MAVEN_PASSWORD" >>~/.gradle/gradle.properties

# package jar
chmod +x gradlew &&
  ./gradlew :$MODULE:clean :$MODULE:prepare
