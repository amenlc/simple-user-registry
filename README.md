# Simple User Registry

This project scopes User Registration and Authentication with JWT and Spring Security

- [Solutions Diagram](solution-diagram.jpg)
- [Sequence Diagram](user-registration-sequence.jpg)

## Prerequisites

You need Java 8 installed

## Usage

Execute the following:

- Run project

```
gradle bootRun
```

- To get a valid JWT token

```
curl --request POST \
  --url http://localhost:8080/authenticate \
  --header 'content-type: application/json' \
  --data '{
	"email":"registeradmin@gmail.com",
	"password":"admin"
}'
```

The command above returns a token like this:

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWdpc3RlcmFkbWluQGdtYWlsLmNvbSIsImlhdCI6MTU4MTUzNDcxMn0.JK7fB8tA9MCjoXd7_k_q6LGSURPts-uangjKrsdTsxM
```

- To register a user

```
curl --request POST \
  --url http://localhost:8080/createUser \
  --header 'authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyZWdpc3RlcmFkbWluQGdtYWlsLmNvbSIsImlhdCI6MTU4MTU1MTE3MX0.lUcXPrGC2h3DSTfvyiciNlLzSSfCct3JH_cNhJyJt2E' \
  --header 'content-type: application/json' \
  --data '{
	"name" : "Juan Rodriguez" ,
	"email" : "juan@rodriguez.org" ,
	"password" : "Paxds22" ,
	"phones" : [
	{
	"number" : "1234567" ,
	"citycode" : "1" ,
	"contrycode" : "57"
	}
	]
}'
```
