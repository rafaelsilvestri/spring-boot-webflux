# About
#### WIP
This Work still In Progress

This is a sandbox project to play with spring framework, specially reactor netty and other tools to 
speed up the development of microservices.

## Running this Application
In order to run the whole environment locally you can start a docker-compose by typing the command 
bellow:
```
./gradlew clean build
docker-compose up --build
```


## Flyway
![logo](https://flywaydb.org/assets/logo/flyway-logo-tm-sm.png) 

[Flyway](https://flywaydb.org/) is a powerful tool that provides database migration, keeping your schema versioned.

In order to apply the schema to the database created by docker-compose spec, you need to run the command bellow:
```
./gradlew flywayMigrate -i -Pflyway.schemas=public -Pflyway.user=postgres -Pflyway.password=password -Pflyway.url=HOST:5432
```

## Gatling
<img src="https://gatling.io/wp-content/uploads/2017/02/Gatling-logo.png" width="250">

[Quickstart](https://gatling.io/docs/current/quickstart/)

##### Runing all simulations
```
./gradlew gatlingRun -Dusers=2 -Dduration=30 -Dbaseurl=http://localhost:8080/hello
```

##### Runing a specific simulations
```
./gradlew gatlingRun-[SimulationClass] -Dusers=2 -Dduration=30 -Dbaseurl=http://localhost:8080/hello
```

##### Testing Endpoints
```
curl -d "@person-data.json" -H "Content-Type: application/json" -X POST http://localhost:8080/v1/person
```

```
curl -H "Content-Type: application/json" -X GET http://localhost:8080/v1/person |jq
```

```
curl -H "Content-Type: application/json" -X GET http://localhost:8080/v1/person?page=0&size=5&sortBy=firstName&sortDirection=desc |jq
```
