# Assignment3-Backend
## Description
This project is our solution to assignment 3, backend. When running the project you can perform the HTTP requests according to the lab specification, just go to the right endpoint in something like postman and perform the correct HTTP method. 

## Endpoints (where {id} is a specific primary key id)
Here are some endpoints that can be used to test the functionality of the project.

### Franchise Endpoints:
1. Get all Characters in franchise: **(GET) localhost:8080/api/v1/franchises/characters/{id}** 

2. Get a specific franchise: **(GET) localhost:8080/api/v1/franchises/{id}**.

3. Get all franchises: **(GET) localhost:8080/api/v1/franchises**

4. Post a franchise: **(POST) localhost:8080/api/v1/franchises**

5. Update a franchise: **(PUT) localhost:8080/api/v1/franchises/{id}**

6. Delete a franchise: **(DELETE) localhost:8080/api/v1/franchises/{id}**

7. Update movies in a franchise: **(PUT) localhost:8080/api/v1/franchises/movies/{id}**

### Movie Endpoints:
1. Get all Movies: **(GET) localhost:8080/api/v1/movies**

2. Get specific Movie: **(GET) localhost:8080/api/v1/movies/{id}**

3. Post a new Movie: **(POST) localhost:8080/api/v1/movies**

4. Update a new Movie: **(PUT) localhost:8080/api/v1/movies/{id}**

5. Delete a Movie: **(DELETE) localhost:8080/api/v1/movies/{id}**

6. Update characters in Movie: **(PUT) localhost:8080/api/v1/movies/characters/1**

### Character Endpoints:
1. Get all Characters: **(GET) localhost:8080/api/v1/character**

2. Get a specific Character: **(GET) localhost:8080/api/v1/character/{id}**

3. Post a new Character: **(POST) localhost:8080/api/v1/character/{id}**


## Installation
1. Install Java version 17 (or later versions).
2. Install Maven (you can run the command `mvn -v` to see if you have it installed).
3. Install SpringBoot.
4. Install DBeaver or PGadmin.
5. Install PostgresSQL. 
6. Create an account at postman (or any other similar service) to test HTTP methods like POST.

## Authors
Vendela Österman

August Danell Håkansson
