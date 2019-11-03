# jungle

A simple web application to authorize and validate network ips.

1. See a list of requests

2. Click on a selected request to trigger the validation

## Prerequisite to run the application
- JDK 1.8.0_211 or higher
- Maven 3.6.1 or higher


## Technologies used
- Spring boot
- H2 database
- JPA/Hibernate
- Logback
- VueJS
- Swagger

## Commands

To build the jar

```
mvn clean package
```

To start the server

```
java -jar <PATH>/jungle-1.0.0.jar
```

or

```
mvn spring-boot:run
```
 
## Startup parameters

Additional
- Debug mode : --debug

## Rules
- ValidationRule1: PROD TO NON-PROD
- ValidationRule2: NON-PROD TO PROD

## SQL (default data)

```
-- request
insert into request(id, sourceip, destinationip, status) values(1, '1.1.1.1', '11.11.11.11', 'NEW');
insert into request(id, sourceip, destinationip, status) values(2, '2.2.2.2', '22.22.22.22', 'NEW');
insert into request(id, sourceip, destinationip, status) values(3, '3.3.3.3', '33.33.33.33', 'NEW');
insert into request(id, sourceip, destinationip, status) values(4, '4.4.4.4', '44.44.44.44', 'NEW');
```

## URLs

- Application root page http://localhost:8080/jungle
- H2 in-memory database http://localhost:8080/jungle/h2 and give 'jdbc:h2:mem:jungle' as the JDBC url
- Swagger API http://localhost:8080/jungle/swagger-ui.html
	

## History

Version 1.0.0 : initial release of the component
