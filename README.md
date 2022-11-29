# Emotional Help

## Business case
Our company provides a psychological assistance service (consultations with psychologists, trainings and therapy).
We are creating a tool that would help us help more people and support our clients.
Our vision is that the first step for understanding yourself and your state is to understand what you feel, what emotions you are experiencing and why.
It is often difficult for people to identify specific emotions, and therefore we want to help them with this first step with the help of an emotion map.
With the help of simple psychological questionnaires, we can help define an emotion that will build a personal map of emotions for each of our users.
If the user is worried that he feels a certain range of emotions and this interferes with the quality of his life,
we will offer him simple tips to cope with emotions or work out his states in more detail using the services of our service.

# Java 11 Sprint Boot Maven application.

# How to run the project

* clone the repo and go to root folder
* ``mvn clean build``
* ``java -jar -Dspring.profiles.active=dev target/*.jar``

Local server is going to deploy on 8080 port of localhost

If you specify profile=prod, the application should start on MySql DB,

else the application should start on h2 database by default (profile=dev)

# Security scanning status
[![CodeQL](https://github.com/emotional-help-competition/backend/actions/workflows/codeql.yml/badge.svg)](https://github.com/emotional-help-competition/backend/actions/workflows/codeql.yml)

## HTTPS


