# ATM-server graduation project
## Cтек проекта:
 1) java 8
 2) spring boot,
 3) spring mvc,
 4) spring data jdbc,
 5) spring security + JWT,
 6) H2 БД,
 7) docker;
 8) JUnit+ Mockito


## Модули проекта:
 1) server -  серверная часть, которая отвечает за хранение пользователей и балансов карт;
 2) client -  клиентская часть, которая запрашивает с банкомата балансы;
 3) common -  набор общих моделей;

## Схема БД: 
имеем таблицу с банковскими картами и имеем таблицу с пользователями, один пользователь может иметь несколько карт.
начальные данные БД и схема находятся тут: 
https://github.com/alexanderkroshev/final_project_sber/tree/merge_client_server/server/src/main/resources/db

## Для запуска проекта необходимо:
```bash
    >>mvn clean install spring-boot:build-image -DskipTests
    >>docker run -p 9091:9091 docker.io/library/client:2.4.5
    >>docker run -p 8080:8080 docker.io/library/server:2.4.5
```
