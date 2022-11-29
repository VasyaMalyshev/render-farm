# Render Farm

Тестовое задание для Render Farm

Стек: Java 17, Spring Boot, Spring Security, JWT, PostgreSQL, Liquibase, Docker


Для выполнения задачи я создал фронтэнд и бекэнд и упаковал в docker-контейнеры.
Liquibase после запуска программы создаст в базе данных таблицы и заполнит их тестовыми данными.
Регистрация пользователя сделана с помощью Spring Security и JWT-токенов.
Данные тестового пользователя: логин: test, пароль: test

Для запуска docker-образов нужен установленный Docker Hub. 
После установки и запуска Docker Hub в IDEA нужно открыть терминал и ввести команду docker-compose up.
Так он запустит образы фронта, бэка и базы данных. После этого можно открыть браузер и открыть http://localhost:3000/login
В открывшейся странице вводим логин: test, пароль: test. После этого сразу перекинет на страницу с задачами и историей изменений статусов в задачах.


Также можно просто выполнить curl-запросы:

Получение истории изменнеий статусов:
curl --location --request GET 'http://localhost:8081/api/v1/status-history'

Залогиниться, в ответ мы получим токен:
curl --location --request POST 'http://localhost:8081/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username" : "test",
    "password" : "test"
}'


Получение всех задач:
curl --location --request GET 'http://localhost:8081/api/v1/task'

Создание задачи:
curl --location --request POST 'http://localhost:8081/api/v1/task' \
--header 'Authorization: Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0Iiwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJpYXQiOjE2Njk2NDgyNzEsImV4cCI6MTY3MzI0ODI3MX0.t_h7LhA07TDUBdjOw0p0NhsrqhnS3I9KabIS3Xm0e2k' \
--header 'Content-Type: application/json' \
--data-raw '{
    "title": "task",
    "description": "description"
}'


