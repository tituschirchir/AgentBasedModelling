#!/usr/bin/env bash
mvn clean;
mvn package;
java -jar target/Agent-Based-Modelling-1.0-SNAPSHOT.jar server dropwizard.config.yml;
