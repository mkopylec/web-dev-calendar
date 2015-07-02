# WebDev Calendar

Coding task implementation.

## How to run application

Run application:

    ./gradlew clean bootRun
    
Test application with different input data:
    
    curl -i -H "Content-Type: application/json" --data @src/test/resources/exact_meetings_found_input.json localhost:8080/meetings
    curl -i -H "Content-Type: application/json" --data @src/test/resources/no_meetings_found_input.json localhost:8080/meetings
    curl -i -H "Content-Type: application/json" --data @src/test/resources/not_enough_meetings_found_input.json localhost:8080/meetings

## How to run integration tests

    ./gradlew clean test
