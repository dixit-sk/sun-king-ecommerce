
# Sun King E-Commerce Microservices

You are working as a backend developer at a growing e-commerce company. The company has been facing challenges with their current monolithic application due to scalability issues during peak shopping seasons (e.g., Black Friday). The business wants to transition to a microservices architecture to improve scalability, resilience, and maintainability.


## HLD

![HLD Screenshot](sun-king-ecommerce-hld.png)


## Microservices

- Service Registery (8761)
- API Gateway (8080)
- Auth (8081)
- Inventory (8082)
- Order (8083)
- Notification (async RabbitMQ)


## Auth Microservice
#### POST /api/v1/auth/login
#### Example 1: User doesn't exist
Body
```json
{
    "email": "user5@email.com",
    "password": "fasdf"
}
```
Reponse (401)
```json
{
    "success": false,
    "message": "User doesn't exist",
    "data": null
}
```
#### Example 2: Invalid credentials
Body
```json
{
    "email": "user1@email.com",
    "password": "user1"
}
```
Reponse (401)
```json
{
    "success": false,
    "message": "Invalid credentials",
    "data": null
}
```
#### Example 3: Successful login
Body
```json
{
    "email": "user1@email.com",
    "password": "user1"
}
```
Reponse (200)
```json
{
    "success": true,
    "message": "Login successful",
    "data": {
        "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzI2OTk0NjEyLCJleHAiOjE3MjcwODEwMTJ9.dbyGbkDUYs0SSIlef7RSIEseCvcMwKUfg8qBob6XQ5o"
    }
}
```



## Inventory Microservice
#### POST /api/v1/inventory/products
#### Example 1
Body
```json
{
    "name": "Product 4",
    "price": 400,
    "count": 40
}
```
Reponse
```json
{
    "success": true,
    "message": "Product added",
    "data": {
        "id": 4,
        "name": "Product 4",
        "price": 400,
        "count": 40
    }
}
```

#### GET /api/v1/inventory/products
#### Example 1
Reponse (200)
```json
{
    "success": true,
    "message": "Products fetched",
    "data": [
        {
            "id": 1,
            "name": "Product 1",
            "price": 100,
            "count": 10
        },
        {
            "id": 2,
            "name": "Product 2",
            "price": 200,
            "count": 20
        },
        {
            "id": 3,
            "name": "Product 3",
            "price": 300,
            "count": 30
        }
    ]
}
```

#### GET /api/v1/inventory/products/{productId}
#### Example 1: Successful (/api/v1/inventory/products/1)
Reponse (200)
```json
{
    "success": true,
    "message": "Product fetched",
    "data": {
        "id": 1,
        "name": "Product 1",
        "price": 100,
        "count": 10
    }
}
```
#### Example 2: Product Not Found (/api/v1/inventory/products/5)
Reponse (400)
```json
{
    "success": false,
    "message": "Product not found",
    "data": null
}
```

#### PUT /api/v1/inventory/products/updateCount/{productId}?increment={increment}
#### Example 1: /api/v1/inventory/products/updateCount/1?increment=10
```json
{
    "success": true,
    "message": "Product count updated",
    "data": {
        "id": 1,
        "name": "Product 1",
        "price": 100,
        "count": 20
    }
}
```
#### Example 2: /api/v1/inventory/products/updateCount/1?increment=-30
```json
{
    "success": false,
    "message": "Insufficient stock for product: Product 1",
    "data": null
}
```



## Order Microservice
#### ** Note: JWT in Auth Header
#### POST /api/v1/orders/
#### Example 1: Successful
Body
```json
{
    "productId": 1,
    "qty": 4
}
```
Reponse
```json
{
    "success": true,
    "message": "Order Placed",
    "data": {
        "id": 1,
        "userId": 1,
        "productId": 1,
        "qty": 4,
        "status": "PLACED"
    }
}
```
#### Example 2: Out Of Stock
Body
```json
{
    "productId": 3,
    "qty": 50
}
```
Reponse
```json
{
    "success": false,
    "message": "Insufficient stock for product: Product 3",
    "data": null
}
```

#### PUT /api/v1/orders/updateOrderStatus/{orderId}?orderStatus={orderStatus}
#### Example 1: /api/v1/orders/updateOrderStatus/1?orderStatus=SHIPPED
```json
{
    "success": true,
    "message": "Order status updated",
    "data": {
        "id": 1,
        "userId": 1,
        "productId": 1,
        "qty": 4,
        "status": "SHIPPED"
    }
}
```
#### Example 2: /api/v1/orders/updateOrderStatus/2?orderStatus=SHIPPED
```json
{
    "success": false,
    "message": "Order not found",
    "data": null
}
```

