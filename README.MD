# README.md

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=dans-la-rue_homeless&metric=alert_status)](https://sonarcloud.io/dashboard?id=dans-la-rue_homeless)

### build with maven

mvn clean install dockerfile:build

### To launch only the app without dependencies

`docker-compose -f docker-compose.yml up -d --no-deps app`

### links

[frontend](https://homeless.lambla.eu/)  
[backend](https://homeless.lambla.eu:9090/swagger-ui.html#/)  

Basic auth:  
username=admin  
password=nimda  

### Sources and inspirations :  

[callicoder](https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/)
