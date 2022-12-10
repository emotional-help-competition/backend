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

# How to run backend application locally?

### Initial set up:
Download and setup java on your PC:
-  check it by running `java --version` command in cmd

### Run steps:
1. Open CLI using cmd:
  -  go to the folder where you downloaded .jar archive
  - `java -jar {folder_path}/{archive_name}.jar`

2. Verify that backend application is working:
  -  Open your browser and go to http://localhost:8080/actuator/health
  -  your status should be UP

Local server is going to deploy on 8080 port of localhost.

### Notes:
See [How to Install JDK in Windows 11?](https://makeuseof.com/windows-11-install-java-jdk/) for more details.


# Security scanning status
[![CodeQL](https://github.com/emotional-help-competition/backend/actions/workflows/codeql.yml/badge.svg)](https://github.com/emotional-help-competition/backend/actions/workflows/codeql.yml)

# Open-API [[Click]](https://emotional-help-competition.github.io/open-api/)

# Deployed backend [[Click]](https://emotional-help.herokuapp.com/swagger-ui/index.html)