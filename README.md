# del-intern-task
 
Бэкенд-часть приложения для учёта клиентов компании «Лига Цифровой Экономики»:
REST API для создания, чтения, обновления и жёсткого удаления клиентов с хранением в PostgreSQL.
 
## Стек
 
- Java 21, Spring Boot 4.1.2, Maven
- Spring Data JPA (Hibernate) + PostgreSQL 17
- Flyway - версия схемы БД через миграции
- Lombok, MapStruct
- springdoc-openapi - Swagger UI из коробки
- Валидация запросов - Jakarta Bean Validation, ошибки через RFC 9457 (`ProblemDetail`)
 
## Требования
 
- JDK 21
- Docker + Docker Compose
 
## Запуск
 
```bash
# 1. Конфигурация окружения
cp .env.example .env        # Windows: copy .env.example .env
# и заполните POSTGRES_PASSWORD
 
# 2. Поднять PostgreSQL (контейнер + volume)
docker compose up -d
 
# 3. Запустить приложение
./mvnw spring-boot:run      # Windows: mvnw.cmd spring-boot:run
```
 
Приложение поднимается на `http://localhost:8080`.
При старте Flyway автоматически применит миграции: создаст таблицу `client_account` и добавит 5 тестовых клиентов.
 
Swagger UI: `http://localhost:8080/swagger-ui/index.html`
 
## API
 
Base URL: `/api/clients`
 
| Метод | Путь | Описание | Успех |
|---|---|---|---|
| POST | `/api/clients` | Создать клиента. Тело: `fullName` (обязателен), `gender` (`M`/`F`). Статус по умолчанию `ACTIVE` | `201` + созданный клиент |
| GET | `/api/clients` | Список всех клиентов | `200` |
| GET | `/api/clients/{id}` | Клиент по id | `200` |
| PUT | `/api/clients/{id}` | Обновить `fullName`/`gender` | `200` + обновлённый клиент |
| DELETE | `/api/clients/{id}` | Жёсткое удаление записи | `204` |
 
Статус клиенту недоступен для изменения через API: `status` - системное поле,
устанавливается при создании (`ACTIVE`) и меняется только бизнес-процессами.
 
### Примеры
 
```bash
# Создание
curl -X POST http://localhost:8080/api/clients \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Иван Иванов","gender":"M"}'
# 201 Created
# {"id":6,"fullName":"Иван Иванов","gender":"M","status":"ACTIVE",
#  "createDttm":"2026-10-25T17:14:45.421281Z","modifyDttm":"2026-10-25T17:14:45.421281Z"}
 
# Обновление
curl -X PUT http://localhost:8080/api/clients/1 \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Иван Иванов-Псевдоним","gender":"F"}'
 
# Удаление
curl -X DELETE http://localhost:8080/api/clients/1
# 204 No Content
```
 
### Ошибки
 
Ошибки возвращаются в формате RFC 9457 (`ProblemDetail`):
 
```json
// 404 - клиент не найден
{"title":"Not Found","status":404,
 "detail":"Клиент с ID 999 не найден.","instance":"/api/clients/999"}
 
// 400 - невалидные данные
{"title":"Bad Request","status":400,
 "detail":"Некорректные данные в запросе","errors":["fullName: Заполните поле ФИО."],
 "instance":"/api/clients"}
```
 
## Модель данных
 
Таблица `client_account` (миграция `V1__create_client_account.sql`):
 
| Колонка | Тип | Описание |
|---|---|---|
| `id` | `bigint` PK, identity | генерируется БД |
| `full_name` | `text NOT NULL` | ФИО клиента |
| `gender` | `varchar(1)` | `M` / `F` |
| `status` | `varchar(20)` | `ACTIVE` / `INACTIVE` |
| `create_dttm` | `timestamptz NOT NULL` | заполняется автоматически (`@CreationTimestamp`) |
| `modify_dttm` | `timestamptz NOT NULL` | обновляется автоматически (`@UpdateTimestamp`) |
 
Тестовые данные - миграция `V2__insert_test_clients.sql` (5 клиентов, включая одного `INACTIVE`).
 