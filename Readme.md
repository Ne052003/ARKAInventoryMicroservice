# Inventory Service

## Description

Inventory Service is a non-blocking API for ARKA business developed with **Spring Boot** to handle requests to create, search, update products and
allows to track updates on the product stock, whether it was due to a new order or by a manual modification from internal staff.

## 🚀 Technologies Used

- Java 21
- Spring Boot 3.5.5
- Spring Webflux
- Spring R2DBC
- MySQL
- Lombok

## 🚀 Aditional Concepts

- Clean architecture
- Domain Driven Design
- Rest Controller

## 📦 Installation and Configuration
### 1️⃣ Clone the Repository
```sh
git clone https://github.com/Ne052003/InventoryMicroservice.git
cd InventoryMicroservice
```

### 2️⃣ Configure the Database
Set the database connection properties or create a .env file with the environment variables and install spring-dotenv:

```properties
    url: r2dbc:mysql://${DB_HOST}:3306/${DB_NAME}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
```

### 3️⃣ Run the Application
```sh
./gradlew bootRun

## Without spring-dotenv
./gradlew bootRun --args='--DB_HOST=**** --DB_NAME=**** --DB_USERNAME=**** --DB_PASSWORD=****'

## 🔧 Future Improvements

✅ Implementation of NotificationService to notify when a product is running out of stock.
