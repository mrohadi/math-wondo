# math-wondo
The purpose of this project is to learn the concept of microservices using java spring boot. I am using Learn Microservices with Spring Boot book as a reference.
Along the way, we are going to build a math battle game application as a result. This book follow agile approach throughout the book to make it more practical.
An agile way of working in which requirements come in the form of user stories.

## Business Requirement
In this project, we want to develop an application to encourage users to train their math skills every day. To begin with, we will have two-digit multiplications presented to users, 
one every time they access the page. They will type their alias (a short name) and the result of the opration, and for that they should use only mental calculation.
After they send the data, a success or failure result will be presented.

In orders to motivated the users, we will also introduce some simple gamification technique: a ranking of users based on points they get when they try the calculation every day,
and also when they succed. We willl show this on the results page.

##### User Story 1
As a user of the application, I want to be presented with a random multiplication that I can solve online--not too easy, so I can use
mental calculation and make my brain work every day.

###### Goal
Create web app for multiplication game

###### Acceptance Criteria
1. Create a basic service with the business logic
2. Create a basic API endpoint to access this service
3. Create a basic web page to ask the users to solve that calculation
