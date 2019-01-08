Overview
--------------

Create REST API service which enables to manage Users in the company. 
Service has to provide functions to insert, remove and search users.

Model
--------------
**USER**

key;firstName;lastName;position;salary

User data should be stored in a file. (file path can be hardcoded)

Insert
--------------

Create new user and save user data to file. 
Key - 8 characters - should be generated automatically. 
firstName, lastName, position and salary - request body - json format.

Request example:

**endpoint:**
/user

**Request body:**
```
{
  firstName: "Jan",
  lastName: "Kowalski",
  position: "Developer",
  salary: 3000
}
```
Error should be returned when:

- User with the given firstName and lastName already exists in file.

Remove
--------------

Remove user data from the file using key coming as a request parameter.

**endpoint**
/user/{key}

Error should be returned when:

- key has more than 8 characters
- User with the given key doesn't exist

Search by key
--------------

Get user data by key.

**endpoint**
/user/{key}

**Response example:**
```
{
  firstName: "Jan",
  lastName: "Kowalski",
  position: "Developer",
  salary: 3000
}
```

Error should be returned when:

- key has more than 8 characters
- User with the given key doesn't exist

Search by salary
----------------

Get all users with salary higher than given in the request:

**endpoint**
/user/?salary=2000

**Response example:**
```
{
  users: [
  {
    firstName: "Jan",
    lastName: "Kowalski",
    position: "Developer",
    salary: 3000
  },
  {
    firstName: "Monika",
    lastName: "Piatek",
    position: "Manager",
    salary: 4000
  }
  ]
}
```
