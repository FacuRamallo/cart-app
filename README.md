# cart-app
Backend api for technic test

## One Box Technic Test 

### TO DO
The test consist in create a back end application for an e-commerce,
It is a simple e-commerce that should be able to
- Create a cart (which will hold products).
- Get a cart information given its id.
- Add one or more products to that cart. A product consists of
the following properties:
  - Id (numerical)
  - Description (alphanumerical)
  - Amount (numerical)
- Delete a cart.
The cart has conditions, lives in memory (no storage is required), is
volatile and has a TTL of 10 minutes.
To implement and solve the challenge you only have one
requirement... use Java! Feel free in all the other options.

## How to run it

In order to build and run this project you should have Java 11:

Execute the following commands:
```
./gradlew run
```
This command will execute this service's *spring boot* application.

## How to run the tests

This project has two types of tests, *acceptance tests*, and *unit tests*.  
Acceptance tests verify from start to end each one of this application's features, including the infrastructure as well as the domain business rules.
Unit tests verify that individual components of this application do what is expected of them.

To compile the project and run all the tests execute:  
`./gradlew build`