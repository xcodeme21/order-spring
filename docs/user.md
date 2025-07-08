# 📘 User API Specification

Semua response mengikuti struktur berikut:

```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "<uuid>",
  "message": "<message>",
  "data": <object/null>
}
```

---

## 🚀 Register User

- **Endpoint**: `POST /api/auth/register`

### 🔸 Request Body
```json
{
  "name": "Agus Siswanto",
  "email": "xcodeme21@gmail.com",
  "password": "mypassword"
}
```

### ✅ Success Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Registration successful",
  "data": {
    "name": "Agus Siswanto",
    "email": "xcodeme21@gmail.com"
  }
}
```

### ❌ Failed Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Email already exists",
  "data": null
}
```

---

## 🔐 Login User

- **Endpoint**: `POST /api/auth/login`

### 🔸 Request Body
```json
{
  "email": "xcodeme21@gmail.com",
  "password": "mypassword"
}
```

### ✅ Success Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Login successful",
  "data": {
    "token": "TOKEN",
    "expired_at": 1751824200
  }
}
```

### ❌ Failed Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Incorrect password. Please try again.",
  "data": null
}
```

---

## ✏️ Update User

- **Endpoint**: `PUT /api/auth/update`
- **Headers**:
  ```
  Authorization: Bearer <token>
  ```

### 🔸 Request Body
```json
{
  "name": "Agus Siswanto",
  "email": "xcodeme21@gmail.com"
}
```

### ✅ Success Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Profile updated successfully",
  "data": {
    "name": "Agus Siswanto",
    "email": "xcodeme21@gmail.com"
  }
}
```

### ❌ Failed Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Invalid or missing Bearer token. Please authenticate.",
  "data": null
}
```

---

## 👤 Get User Profile

- **Endpoint**: `GET /api/auth/profile`
- **Headers**:
  ```
  Authorization: Bearer <token>
  ```

### ✅ Success Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Profile fetched successfully",
  "data": {
    "name": "Agus Siswanto",
    "email": "xcodeme21@gmail.com"
  }
}
```

### ❌ Failed Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Token is invalid or expired.",
  "data": null
}
```

---

## 🚪 Logout User

- **Endpoint**: `POST /api/auth/logout`
- **Headers**:
  ```
  Authorization: Bearer <token>
  ```

### ✅ Success Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Logout successful. Token invalidated.",
  "data": {
    "message": "Logged out"
  }
}
```

### ❌ Failed Response
```json
{
  "app_name": "Order Spring",
  "build": "1",
  "version": "1.0.1.3",
  "id": "uuid",
  "message": "Token is invalid or already logged out.",
  "data": null
}
```

---

> 🛡️ All authenticated routes require a valid Bearer token in the `Authorization` header.