# pact-study

I'm learning more about Pact for consumer-driven testing.

This repository contains:

* a service consumer in subproject `simple-client`
* a service producer in subproject `customer-service`
* a pact broker in `docker-compose.yml` file

## How to run

1. start the pact broker with `docker-compose up`
2. in another terminal, generate the pacts with `gradle :simple-client:pactPublish`
3. verify the pact with `gradle :customer-service:test` or `gradle :customer-service:pactVerify`

## Other stuff

I'm also learning a lot about gradle, specifically:

- Kotlin DSL
- Custom sourceSets
- buildSrc for project plugins