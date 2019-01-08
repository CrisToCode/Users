Overview
--------------

Create REST API service which enables to manage Users in the company. 
Service has to provide functions to insert, remove and search users.

Model
--------------
USER
key;firstName;lastName;position;salary

User data should be stored in a file. (file path can be hardcoded)

Insert
--------------

Create new user and save user data to file. 
Key - 8 characters - should be generated automatically. 
firstName, lastName, position and salary - request body - json format.

Request example:

endpoint:
/user

Request body:
{
  firstName: "Jan",
  lastName: "Kowalski",
  position: "Developer",
  salary: 3000
}

Remove
--------------

Remove user data from the file using key comming as a request parameter.

endpoint
/user/{key}

Search
--------------

Get user data by key.

endpoint
/user/{key}

Response example:
{
  firstName: "Jan",
  lastName: "Kowalski",
  position: "Developer",
  salary: 3000
}
