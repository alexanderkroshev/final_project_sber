# ATM-server graduation project
## Cтек проекта:

 1) spring boot,
 2) spring mvc,
 3) spring data jdbc,
 4) spring security + JWT,
 5) H2 БД,
 6) docker;

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
