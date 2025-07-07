
# Customer API Spec

## Register Customer

- **Endpoint**: `POST /api/auth/register`

### Request Body
```json
{
  "name": "Agus Siswanto",
  "email": "xcodeme21@gmail.com",
  "password": "mypassword"
}
```

### Success Response
```json
{
  "data": {
    "name": "Agus Siswanto",
    "email": "xcodeme21@gmail.com",
    "password": "mypassword"
  },
  "error_message": null,
  "status": 200
}
```

### Failed Response
```json
{
  "data": null,
  "error_message": "Invalid request: name, email, and password are required.",
  "status": 400
}
```

---

## Login Customer

- **Endpoint**: `POST /api/auth/login`

### Request Body
```json
{
  "email": "xcodeme21@gmail.com",
  "password": "mypassword"
}
```

### Success Response
```json
{
  "data": {
    "token": "TOKEN",
    "expired_at": 1751824200
  },
  "error_message": null,
  "status": 200
}
```

### Failed Response
```json
{
  "data": null,
  "error_message": "Incorrect password. Please try again.",
  "status": 401
}
```

---

## Update Customer

- **Endpoint**: `PUT /api/auth/update`
- **Headers**:
  ```
  Authorization: Bearer <token>
  ```

### Request Body
```json
{
  "name": "Agus S. Wijaya",
  "email": "aguswijaya21@gmail.com"
}
```

### Success Response
```json
{
  "data": {
    "name": "Agus S. Wijaya",
    "email": "aguswijaya21@gmail.com"
  },
  "error_message": null,
  "status": 200
}
```

### Failed Response
```json
{
  "data": null,
  "error_message": "Invalid or missing Bearer token. Please authenticate to update profile.",
  "status": 401
}
```

---

## Get Customer

- **Endpoint**: `GET /api/auth/profile`
- **Headers**:
  ```
  Authorization: Bearer <token>
  ```

### Success Response
```json
{
  "data": {
    "name": "Agus Siswanto",
    "email": "xcodeme21@gmail.com"
  },
  "error_message": null,
  "status": 200
}
```

### Failed Response
```json
{
  "data": null,
  "error_message": "Invalid or missing Bearer token. Please authenticate to access this resource.",
  "status": 401
}
```

---

## Logout Customer

- **Endpoint**: `POST /api/auth/logout`
- **Headers**:
  ```
  Authorization: Bearer <token>
  ```

### Success Response
```json
{
  "data": {
    "message": "Logout successful. Token has been invalidated."
  },
  "error_message": null,
  "status": 200
}
```

### Failed Response
```json
{
  "data": null,
  "error_message": "Token is invalid or has already been logged out.",
  "status": 401
}
```

---

> 🛡️ All authenticated routes require a valid Bearer token in the `Authorization` header.
