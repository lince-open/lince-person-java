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
* Spring Actuator

![](https://github.com/lince-open/lince-person-java/workflows/Java%20CI/badge.svg)
[![Known Vulnerabilities](https://snyk.io/test/github/lince-open/lince-person-java/badge.svg)](https://snyk.io/test/github/pedrozatta/lince-person-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-person-java&metric=coverage)](https://sonarcloud.io/dashboard?id=lince-open_lince-person-java)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-person-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=lince-open_lince-person-java)

## Docker Hub

https://hub.docker.com/repository/docker/linceopen/lince-person

mvn clean package dockerfile:build

docker run  --name lince-person -p 8080:8080 -t lince-open/lince-person:latest

docker tag lince-open/lince-person:latest linceopen/lince-person:0.0.2

docker push linceopen/lince-person:0.0.2

docker tag lince-open/lince-person:latest linceopen/lince-person:latest

docker push linceopen/lince-person:latest

###GCLOUD

docker pull linceopen/lince-person:0.0.2

docker tag linceopen/lince-person:0.0.2 gcr.io/lince-work/lince-person:0.0.2

gcloud docker -- push gcr.io/lince-work/lince-person:0.0.2

###H2

docker run --name lince-person \
-e LINCE_PERSON_PORT='8080' \
-e LINCE_PERSON_DATASOURCE_URL='jdbc:h2:file:~/lince-person' \
-e LINCE_PERSON_DATASOURCE_USER='linceperson' \
-e LINCE_PERSON_DATASOURCE_PASS='lincepass' \
-e LINCE_LOG_LEVEL='WARN' \
-p 51001:8080 \
-t linceopen/lince-person:latest

###Mysql

docker network create --driver bridge lincenetwork
 
docker run --name lincemysql --network lincenetwork --hostname lincemysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=lince -d mysql:latest

docker run --name lince-person \
--network lincenetwork \
-e LINCE_PERSON_PORT='8080' \
-e LINCE_PERSON_DATASOURCE_URL='jdbc:mysql://lincemysql:3306/linceperson' \
-e LINCE_PERSON_DATASOURCE_USER='linceperson' \
-e LINCE_PERSON_DATASOURCE_PASS='lincepass' \
-e LINCE_PERSON_DATASOURCE_DIALECT='org.hibernate.dialect.MySQL5InnoDBDialect' \
-e LINCE_LOG_LEVEL='WARN' \
-p 51001:8080 \
-t linceopen/lince-person:latest


####Script
docker exec -it lincemysql mysql -u root -p
 
CREATE USER 'linceperson'@'%' IDENTIFIED BY 'lincepass';
CREATE DATABASE linceperson;
GRANT ALL PRIVILEGES ON linceperson.* TO 'linceperson'@'%';

## Execução
mvn spring-boot:run

