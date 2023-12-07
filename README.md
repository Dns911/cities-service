# Cities service

## Stack

- Java 17
- Spring Boot 3.2.0
- Gradle 8.5
- PostgreSQL

## Database

Project database is PostgreSQL, with PORT: 5432
- HOST_NAME:5432/db_cities_cont and default (public) schema.
- Or you can run docker container with Postgres DB (see Local start p.1)

## Local start

1. With docker
 - open terminal in project folder and run comand: docker-compose up

2. Without docker (IDE + local DB)
 - Open IDE, set local variables DB_PASSWORD={user_db_password};DB_USERNAME={user_db_username};DB_HOST={user_db_host}
 - Setup local DB (see Database p. 1)
 - Open in browser [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui) (on http://localhost:8080/login page use credentials) 

## Credentials
_User_ (allowed all endpoints exclude edit)   
username: user  
password: pass

_Editor_ (allowed all endpoints)    
username: editor        
password: pass


_Admin_ (NOT allowed all endpoints)   
username: admin     
password: pass

## Swagger

Swagger documentation can find:

[http://HOST_NAME:8080/swagger-ui](http://localhost:8080/swagger-ui)