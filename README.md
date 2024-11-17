# E-Commerce Application

## Overview
This project is a feature-rich e-commerce platform built using **Spring Boot**. It allows users to browse products, manage their cart, place orders, and process payments through the **Stripe** payment gateway.

---

## Features
- **User Management**: Register and retrieve user information.
- **Product Management**: Add, view, and search products by category.
- **Cart Management**: Add, view, and remove items from the cart.
- **Order Management**: Place orders and view order history.
- **Payment Integration**: Pay securely using **Stripe**.


## Installation

### Prerequisites
1. **Java 17** or higher
2. **Maven**
3. **PostgreSQL** installed and running
4. Stripe account with API keys

### Clone the Repository
git clone https://github.com/your-repo/ecommerce-app.git
cd ecommerce-app

##Configuration
###Database Configuration
Update application.properties with your PostgreSQL credentials:

spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password

###Stripe Configuration
Add your Stripe secret key to application.properties:
stripe.api.key=sk_test_your_secret_key_here

##Running the Application
###Install dependencies:
mvn clean install

###Start the application:
mvn spring-boot:run
The application will be available at http://localhost:8080.


