# feature-flag-service

## Assumptions
Usually a feature flag implementation is a configuration thing. For exaxmple in Java/Spring Boot applications I would
imagine it would make much more sense to cover features behind `@Profile('myprofile)` or `@ConditionalOnProperty(...)`.

Then as an admin of the environment or just a developer I would perhaps inject specific environmental variables to the env,
and this would turn on or off my features. This could be done for example via kubernetes pods/secrets.

However this task claimed that we need a functionality that allows to create and modify some data in the runtime, therefore
I decided to create a simple CRUD with basic auth, delivering 3 requirements for this service.

In the app there are two controllers that utilize described requirements, there is also attached POSTMAN collection as JSON, 
in the root directory of this project. You can use it to test the application.

There is also some basic testing conducted using JUnit and MockMVC.

## Get started

Just start the application and use POSTMAN collection to try the endpoints.

Basic auth is configured as following:
- `admin:password` - user with ADMIN role
- `user:password` - user with USER role

The app runs on H2 DB for testing purposes. You can go to http://localhost:8080/h2-console (credentials `a:a`) and 
inspect the database.

## Ideas for improvements

- more sophisticated security mechanism, probably token based like JWT,
- spring profiles with different configurations,
- more "production friendly" database then H2, for instance at least embedded postgres,
- database management for migrations like liquibase or flyway,
- E2E test (maybe docker container testing),
- minor improvements like checkstyle, code coverage etc,
- dockerized env, maybe docker compose with standalone database,
- maybe some design patterns in case application would grow, but that would depend on use cases
