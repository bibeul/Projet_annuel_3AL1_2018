#!/bin/bash
sudo /opt/lampp/lampp start
cd api_PA
npm start
cd ..
cd client_lourd
mvn clean install
java -jar target/hackme.jar
