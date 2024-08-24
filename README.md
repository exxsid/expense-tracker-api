# Expense Tracker REST API

---
Spring Boot RESTful application. This application has authentication
and CRUD functions for expenses.

## Technologies

- Java 17
- Spring Boot
- PostgreSQL

## API Endpoints
### Authentication

**Sign Up**

POST `api/v1/auth/signup`

Param:
- `userName` String
- `password` String

**Sign In**

POST `api/v1/auth/signin`

Param:
- `userName` String
- `password` String

### Expense

**Expense list of a User**

GET `api/v1/expenses/{userId}`

Path Variable:
- `userId` Long

**Details of specific expense**

GET `api/v1/expenses/details/{expenseId}`

Path Variable:
- `expenseId` Long

POST `api/v1/expenses/add`

Param:
- `amount` Double
- `category` String
- `note` String _optional_
- `description` String _optional_
- `userId` Long

PUT `api/v1/expenses/update`

Request Body:
```json
{
  "amount": 0.0,
  "category": "string",
  "note": "string",
  "description": "string"
}
```

DELETE `api/v1/expenses/delete/{expenseId}`

Path Variable:
- `expenseId` Long