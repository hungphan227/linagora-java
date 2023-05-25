# how to run

change baseurl of external service in src/main/resources/application.properties

run following commands:

mvn clean package

java -jar target/coding-exercise-1.0-SNAPSHOT.jar

# how to use the application

use a http tool to call api, example:

curl http://localhost:8080/api/recent_purchases/aaa

# note

I made an assumption of the external service api GET /api/purchases/by_user/:username?limit=5: 

- it will respond with http code 400 BAD REQUEST in case user is not found
