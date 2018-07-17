@echo off
cd client_lourd
java -Djava.library.path=src/main/resources/lib/natives -jar target/hackme-jar-with-dependencies.jar
