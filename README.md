## Introduction
This repository represents automated UI test for https://www.demoblaze.com website.

## Tech stack
* **TestNG** - as a testing framework;
* **Selenium 4** - for UI web tests;
* **Rest Assured** - for sending http requests;
* **Allure** - for reporting.

## How to run the tests
### Prerequisites
[Docker](https://docs.docker.com/install/) installed

### Run tests in Chrome
1. Execute the following command to pull docker image and start a container:
```
docker run --env VNC_NO_PASSWORD=1 --name selenium_standalone_chrome -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:126.0-chromedriver-126.0-grid-4.30.0-20250323
```
2. Start tests by executing this command:
```
mvn clean test -DisRemote=true -Dbrowser=chrome
```

### Run tests in Firefox
1. Execute the following command to pull docker image and start a container:
```
docker run --env VNC_NO_PASSWORD=1 --name selenium_standalone_firefox -d -p 4445:4444 --shm-size="2g" selenium/standalone-firefox:131.0-geckodriver-0.36-grid-4.30.0-20250323
```
2. Start tests by executing this command:
```
mvn clean test -DisRemote=true -Dbrowser=firefox
```

### Generate test execution reports
1. Install [Allure](https://allurereport.org/docs/install/)
2. Execute the following commands:
```
cd target
allure serve
```
The generated report will be automatically opened in a browser tab.