#!/bin/bash
java -server $JAVA_OPTS -XX:MinRAMPercentage=60.0 -XX:MaxRAMPercentage=90.0 -XX:+UseParallelGC -jar /usr/app/quarkus-app-runner.jar
