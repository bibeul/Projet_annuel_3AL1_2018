NET START wampapache64
NET START wampmysqld64
cd api_PA
npm start
cd ..
cd client_lourd
mvn clean install
java -jar target/hackme.jar
