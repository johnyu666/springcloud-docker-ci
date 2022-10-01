#!/bin/bash

mvn clean package
cp $(find . -name *.jar) ~/adocker/jars

cd ~/adocker/
docker-compose down
docker rmi -f myimgs
docker build -t myimgs .
docker-compose up -d


