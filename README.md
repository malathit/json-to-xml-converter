# Json2Xml

This is a simple rest webservice that converts json input to xml format

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

```
git clone https://github.com/malathit90/json-to-xml-converter.git
mvn spring-boot:run
```
The server will be up and running on port 8080. Go to `http://localhost:8080/swagger-ui.html`

### Prerequisites

```
Java version 1.8
Maven version 3.x
```

## Running the tests
```
mvn clean test
```

### Break down into end to end tests

There are 2 basic test cases. 
- One test case gives valid json in `src/test/resources/example.json` file as input to the rest api and check if the generated xml is valid as given in `src/test/resources/example.xml`
- The other test case gives an invalid json and checks if a bad request(400) response is given

### How Json to xml conversion happens
The file `src/test/resources/example.json` contains a valid json input. The xml conversion for the example.json file can be found at `src/main/resources/example.xml`

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Json](https://json.org/) - Used to parse and read json
* [Swagger](http://swagger.io) - To create rest ui interface

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE.md) file for details
