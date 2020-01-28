# lince-person-java
Microservice de Cadastro de Pessoas, permite a inclusão, alteração exclusão e pesquisa de Pessoas.
Quando uma pessoa é adicionada, o usuário autenticado é registrado como owner.

* Solução
* Persistencia Spring Data e JPA
* Autenticação com Spring Security e uso de Header
* Testes Unitários com JUnit/Spock/Groove
* Testes Funcionais com JUnit/Spock/Groove
* Swagger2
* Docker

![](https://github.com/lince-open/lince-person-java/workflows/Java%20CI/badge.svg)
[![Known Vulnerabilities](https://snyk.io/test/github/lince-open/lince-person-java/badge.svg)](https://snyk.io/test/github/pedrozatta/lince-person-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-person-java&metric=coverage)](https://sonarcloud.io/dashboard?id=lince-open_lince-person-java)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-person-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=lince-open_lince-person-java)

## Docker Hub

https://hub.docker.com/repository/docker/linceopen/lince-person

mvn clean package dockerfile:build

docker run  --name lince-person -p 8080:8080 -t lince-open/lince-person:latest

docker tag lince-open/lince-person:latest linceopen/lince-person:latest

docker push linceopen/lince-person:latest

## Execução
mvn spring-boot:run

