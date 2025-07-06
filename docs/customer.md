# Customer API Spec

## Register Customer
- Endpoint : POST /api/auth/register

Request Body :
```json
{
  "full_name" : "Agus Siswanto",
  "email" : "xcodeme21@gmail.com",
  "password" : "mypassword"
}
```

Response Body :

### Success
```json
{
    "data" : {
        "full_name" : "Agus Siswanto",
        "email" : "xcodeme21@gmail.com",
        "password" : "mypassword"
        },
    "error_message" : null,
    "status" : 200
}
```

### Failed
```json
{
    "data" : null,
    "error_message" : "Invalid request: full_name, email, and password are required.",
    "status" : 400
}
```

## Login Customer
- Endpoint : POST /api/auth/login

Request Body :
```json
{
  "email" : "xcodeme21@gmail.com",
  "password" : "mypassword"
}
```

Response Body :

### Success
```json
{
    "data" : {
      "token" : "TOKEN",
      "expired_at" : 1751824200 // millisecond
    },
    "error_message" : null,
    "status" : 200
}
```

### Failed
```json
{
    "data" : null,
    "error_message" : "Incorrect password. Please try again.",
    "status" : 401
}
```

## Update Customer

## Get Customer

## Logout Customer