#!/usr/bin/env just --justfile

build:
  mvn -B -ntp -DskipTests package

downloadCodeGeneratorCli:
  mvn dependency:copy -Dartifact=com.javaaidev.easyllmtools:code-generator-cli:0.1.0 -DoutputDirectory=target -Dmdep.stripVersion=true

generateCodeGetWeather: build downloadCodeGeneratorCli
  java -jar target/code-generator-cli.jar simple \
    --output=tool-get-weather-fake \
    --artifact-id=get-weather --artifact-version=0.1.0 \
    get-weather.json

installGetWeather:
  mvn -B -ntp -f examples/tool-get-weather-fake/pom.xml install

generateCodeExchangeRate: build downloadCodeGeneratorCli
  java -jar target/code-generator-cli.jar openapi \
      --output=target/exchange-rate \
      --artifact-id=exchange-rate --artifact-version=0.1.0 \
      exchangerate-openapi.json

installCodeExchangeRate: generateCodeExchangeRate
  mvn -B -ntp -f target/exchange-rate/pom.xml install