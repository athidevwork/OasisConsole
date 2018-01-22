# OasisConsole

##### Oasis Admin Console will be used to get config and other data for Oasis application deployed on the server.

This tool is built on restful api exposing the config data from database and reads the config data from properties.

There is a web context also exposed in the project that would serve as the GUI.

## General Assumptions
Developer is expected to have java, gradle, git setup in his machine to start development of this project.

Eclipse IDE was used to develop this project. So it would be easier to view this using eclipse, but is not limited to use that IDE.

## Github
To get the project from github, type in the command below,

> git clone https://github.com/athidevwork/OasisConsole.git

## Build
Oasis Console Tool is built using gradle.

### Build the project
> ./gradlew build

### Run the project/server
> ./gradlew run

## Deploy
gradle to be used for deploying the project

## Oasis Console GUI

Oasis Console GUI would show up if the following URL is entered in a browser. Security features are not yet enabled for the application.

> http://localhost:2222/WebContent/

## Test for Restful API

### Config Restful api can be tested using curl or any rest client.

#### Get json response
> curl -H "application/json" http://localhost:2222/rest/oasisconsole/config/mag20171se

#### Get xml response
> curl -H "application/xml" http://localhost:2222/rest/oasisconsole/config/mag20171se

#### Get html response
> curl -H "text/html" http://localhost:2222/rest/oasisconsole/htmlconfig/mag20171se


### Issue Restful api can be tested using curl or any rest client.

#### Get json response
> curl -H "application/json" http://localhost:2222/rest/oasisissue/policy?env=wvmic20141qa&policy=PL402919

#### Get xml response
> curl -H "application/xml" http://localhost:2222/rest/oasisissue/policy?env=wvmic20141qa&policy=PL402919

#### Get html response
> curl -H "text/html" http://localhost:2222/rest/oasisissue/html/policy?env=wvmic20141qa&policy=PL402919


