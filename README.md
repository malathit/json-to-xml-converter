# Json2Xml

This is a simple rest webservice that converts json input to xml format

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

```
git clone https://github.com/malathit90/json-to-xml-converter.git
mvn spring-boot:run
```
Go to `http://localhost:8080/swagger-ui.html`. Call the api `/api/v1/json2xml`.

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
The json to xml conversion doesn't necessarily happen in a single way. In this project, the following is the way xml conversion happens.
Also, the code takes as input only valid json objects and arrays.

#### Json Number
A json element with a number as a value should map to an XML element named `<number>`.

    {"a" : 5}

The above json could be mapped as :

    <object>
        <number name='a'>
            5
        </number>
    </object> 

#### Json String
A json element with a string as a value should map to an XML element named `<number>`.

    {"s" : "cjdb"}

The above json could be mapped as :

    <object>
        <string name='s'>
            cjdb
        </string>
    </object> 


#### Json Boolean
A json element with a boolean (true or false) as a value should map to an XML element named `<boolean>`.

    {"b" : true}

The above json could be mapped as :

    <object>
        <boolean name='b'>
            true
        </boolean>
    </object> 

#### Json Array
A json element with a array as a value should map to an XML element named `<array>`.


    [ 1, 4, "jur", { "firstName" : "xyx" }]

The above json could be mapped as :

    <object>
        <array>
            <number>1</number>
            <number>4</number>
            <string>jun</string>
            <object>
                <string name = 'firstName'>xyx</string>
            </object>
        </array>
    </object> 

#### Json NULL
A json element with a number as a value should map to an XML element named `<number>`.

    {"a" : null}

The above json could be mapped as :

    <object>
        <null name = 'a' />
    </object> 


### Example files
The file `src/test/resources/example.json` contains a valid json input. The xml conversion for the example.json file can be found at `src/main/resources/example.xml`

## Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Json](https://json.org/) - Used to parse and read json
* [Swagger](http://swagger.io) - To create rest ui interface

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [releases on this repository](https://github.com/malathit90/json-to-xml-converter/releases).

## License

This project is licensed under the Apache License - see the [LICENSE.md](LICENSE.md) file for details
