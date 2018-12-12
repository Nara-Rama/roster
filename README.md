## Overview ##

A small application for crud operation for Student entity.

## Technology Stack ##

Java 8, Spring Boot, Spring Security, Spring Data Jpa, MySQL, JUnit, Gradle

### Set up ###
* Create a mysql schema
  => CREATE SCHEMA IF NOT EXISTS roster;
* Change the jdbc url in src/main/resources/application.properties if necessary

### Build & Run ###
* Build and run tests
  => ./gradlew clean build
* Run server
  => ./gradlew clean bootRun
* Create a student
  => curl -X POST http://localhost:8080/students/ -H 'Authorization: Basic dXNlcjpwYXNzd29yZA==' -H 'cache-control: no-cache' -H 'content-type: application/json' -d '{"username": "john-doe", "firstname": "John", "lastname": "Doe", "email": "john.doe@google.com"}'
* Get a student
  => curl http://localhost:8080/students/1 -H 'Authorization: Basic dXNlcjpwYXNzd29yZA==' -H 'cache-control: no-cache' -H 'content-type: application/json'