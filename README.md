# Spring Boot Oauth2 using JWT with PostgreSQL

Reference : [Medium Article with the same title](https://medium.com/@dassum/securing-spring-boot-rest-api-with-jsonweb-token-and-jdbc-token-store-67558a7d6c29)

A sample Spring Boot Application for Securing REST APIs with Oauth2 using JSON Web Token (JWT) that will be stored in a PostgreSQL Database. JdbcTokenStore is used to save the token issued to the users</br>

This application can be used as a template to quick start your spring boot REST API project with a fully functional security module.


Main building blocks
========

The following libraries have been added to the basic spring-boot + PostgreSQL packages:</br>

* spring-security-oauth2: 2.3.8.RELEASE
* spring-security-jwt: 1.1.1.RELEASE

To run the application: </br>

* mvn spring-boot:run 


Testing the application
=========

The following default information will be used to test the application:</br>

- client: testjwtclientid </br>
- client-secret: hello </br>
- non-Admin username and password: john.doe and hello
- admin user and password: admin and hello


<b>Generate an Access Token</b>

```
curl testjwtclientid:hello@localhost:8080/spring-boot-authJdbc/oauth/token -d grant_type=password -d username=john.doe -d password=hello
```

result will be: 

```
{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE4MTE3MDksInVzZXJfbmFtZSI6ImpvaG4uZG9lIiwiYXV0aG9yaXRpZXMiOlsiU1RBTkRBUkRfVVNFUiJdLCJqdGkiOiIzZmQ3YTNkZC01MDBiLTQ2NWQtOTg0NS0zYWY1NDc5MzQyNjUiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.RAyzeeiUOzAWZTzWamI_iuaQBzVVcKRqya9LPBn3GPg",
"token_type":"bearer",
"refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqb2huLmRvZSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiIzZmQ3YTNkZC01MDBiLTQ2NWQtOTg0NS0zYWY1NDc5MzQyNjUiLCJleHAiOjE4NjQ5NjI5MDksImF1dGhvcml0aWVzIjpbIlNUQU5EQVJEX1VTRVIiXSwianRpIjoiN2MxNWIzOGQtM2ZiZi00OGVlLThkZDUtYzU2MjZlNjBiNDJlIiwiY2xpZW50X2lkIjoidGVzdGp3dGNsaWVudGlkIn0.GIwyjdbXPctod6pWt8Lo3TCRBG287eOPKDe4zSFiCWc",
"expires_in":6048799,
"scope":"read write",
"jti":"3fd7a3dd-500b-465d-9845-3af547934265"}
```
     
<b>Validate an Access Token</b>

    
```
curl testjwtclientid:hello@localhost:8080/spring-boot-authJdbc/oauth/check_token -d "token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE4MTE3MDksInVzZXJfbmFtZSI6ImpvaG4uZG9lIiwiYXV0aG9yaXRpZXMiOlsiU1RBTkRBUkRfVVNFUiJdLCJqdGkiOiIzZmQ3YTNkZC01MDBiLTQ2NWQtOTg0NS0zYWY1NDc5MzQyNjUiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.RAyzeeiUOzAWZTzWamI_iuaQBzVVcKRqya9LPBn3GPg"
```

result will be:

```
{"active":true,
"user_name":"john.doe",
"authorities":["STANDARD_USER"],
"client_id":"testjwtclientid"}
```

<b>Refresh an Access Token</b>

```
curl testjwtclientid:hello@localhost:8080/spring-boot-authJdbc/oauth/token -d "grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqb2huLmRvZSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiI1MWYzNTZkZC1mZjU4LTQ2NDMtYjZiNS0wOWYzMmYzYjdlNDkiLCJleHAiOjE4NjQ5NjU3MDYsImF1dGhvcml0aWVzIjpbIlNUQU5EQVJEX1VTRVIiXSwianRpIjoiNzQ4YjBjMzMtYTExYy00YjA0LTgwZjYtNGI2NjM5ZTFmOTVkIiwiY2xpZW50X2lkIjoidGVzdGp3dGNsaWVudGlkIn0.MOuC6f5L8c5-EKJhjahFIiUEfkxZZPB5poNCqhFCFAQ"
```

result will be:


```
{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE4MTQ4MDIsInVzZXJfbmFtZSI6ImpvaG4uZG9lIiwiYXV0aG9yaXRpZXMiOlsiU1RBTkRBUkRfVVNFUiJdLCJqdGkiOiI5ZTBmMjFjYy0wZWM0LTRmNGYtOWM3MS1iNmVmOTRiNWI0YzMiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.rKS3O2MuKSgvNG3qkQ0kzMpNzk6PZClq0kI5OvwI9J8",
"token_type":"bearer",
"refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqb2huLmRvZSIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiI5ZTBmMjFjYy0wZWM0LTRmNGYtOWM3MS1iNmVmOTRiNWI0YzMiLCJleHAiOjE4NjQ5NjYwMDIsImF1dGhvcml0aWVzIjpbIlNUQU5EQVJEX1VTRVIiXSwianRpIjoiNDA5NzM5NTItOTFkOC00ZDE4LWJkZDAtMjgwNjAwNGI4ODNjIiwiY2xpZW50X2lkIjoidGVzdGp3dGNsaWVudGlkIn0.pdGj-kCk8v-li5SZH5xkFhk-pwtmg1hi3CU8Z5-gxlA",
"expires_in":6048799,
"scope":"read write",
"jti":"9e0f21cc-0ec4-4f4f-9c71-b6ef94b5b4c3"}
```


<b>Use Token to access REST API Services</b>


```
curl http://localhost:8080/spring-boot-authJdbc/api/employee -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MTE4MTQ4MDIsInVzZXJfbmFtZSI6ImpvaG4uZG9lIiwiYXV0aG9yaXRpZXMiOlsiU1RBTkRBUkRfVVNFUiJdLCJqdGkiOiI5ZTBmMjFjYy0wZWM0LTRmNGYtOWM3MS1iNmVmOTRiNWI0YzMiLCJjbGllbnRfaWQiOiJ0ZXN0and0Y2xpZW50aWQiLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXX0.rKS3O2MuKSgvNG3qkQ0kzMpNzk6PZClq0kI5OvwI9J8"
```


result will be:



```
[{"id":1,"name":"Jack","address":"USA","email":"jack@gmail.com"}]
```

