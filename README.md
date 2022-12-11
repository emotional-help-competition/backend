# Emotional Help
### Business case
That is often difficult to understand yourself and personal state, so we want to help our clients with this first step. Our web application provides psychological questionnaires that were built to define a personal emotional map. If the user is worried that he feels some range of emotions, we will offer him simple tips to work out his states in more detail.
### Security scanning status
[![CodeQL](https://github.com/emotional-help-competition/backend/actions/workflows/codeql.yml/badge.svg)](https://github.com/emotional-help-competition/backend/actions/workflows/codeql.yml)
### Technical details
Our backend application uses Java 11 and SpringBoot framework for web-application development.
### How to run backend application locally?
#### Initial set up:
Download and setup java on your PC:
- check it by running `java --version` command in cmd
#### Run steps:
1. Open CLI using cmd:
    - go to the folder where you downloaded .jar archive
    - `java -jar {folder_path}/{archive_name}.jar`
2. Verify that backend application is working:
    - open your browser and go to http://localhost:8080/actuator/health
    - your status should be UP
      Local server is going to deploy on 8080 port of localhost.
#### Notes:
See [How to Install JDK in Windows 11?](https://makeuseof.com/windows-11-install-java-jdk/) for more details.
### Useful links:
- Open-API documentation [[Click]](https://emotional-help-competition.github.io/open-api/)
- Deployed backend [[Click]](https://emotional-help.herokuapp.com/swagger-ui/index.html)