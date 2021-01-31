# Vehicle registration system

Vehicle registration 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

You can find my Kanban board [here](https://20novsimontest.atlassian.net/secure/RapidBoard.jspa?rapidView=6&projectKey=PRO2).

### Prerequisites

* Java SE 11

* An instance of MySQL server 5.7

### Installing

You can download a working jar from [here](linky linky).
The provided jar build only works with a locally hosted MySQL instance with a schema called `hobby_web_app` with login details of `root` & `root`.

If you wish to build the project yourself here are the instructions.

Import this project from github as a Maven project.

You will need to edit the contents of the `application.properties` class with your own database details.

```
spring.datasource.url=jdbc:mysql://localhost:3306/hobby_web_app
spring.datasource.username=username
spring.datasource.password=password
```

Once this is done you should test the program using the provided tests. If it all goes by you can build the project with the command `mvn clean package`. The working jar will be called `hobby-web-app-1.0.war` in the `target` folder.

## Running the tests

You can run all all the tests by using `mvn test` in a command prompt at the project folder. Make sure you have entered database details in the `application.properties` files as the backend tests use a test h2 database however the Selenium tests need to run on a live system.

All the tests are located in the `src/test` folder.

The tests make sure that the database connection is functional and that the user input is handled correctly.

We currently have >90% coverage in the REST & services methods.
![text](https://i.imgur.com/TfHM8mJ.png)
![text](https://i.imgur.com/4hgxibt.png)

The report of the front end tests are located in the `target/reports/report.html` file.

### Unit Tests 

The unit tests make sure that the interaction with the database is correct.

They are located in the `com.qa.hobby.service` package of the test folder.

### Integration Tests 

The integration tests are here to check if the correct actions are done when giving input to the controllers. These tests do not require a database connection as database results are mocked.

They are located in the `com.qa.hobby.controllers` package.

### And coding style tests

We are currently not using coding style tests.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Simon Forster** - *Continued work* - [sforsteracademytrainee](https://github.com/sforsteracademytrainee)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*