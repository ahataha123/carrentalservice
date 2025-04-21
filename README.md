🚗 Car Rental Service – REST API
This Spring Boot application provides a simple RESTful Car Rental Service with functionality for:

User registration
Listing all available cars
Booking a car
Returning a car
Viewing booked cars
(Planned) Prices stored in USD
(Planned) currency conversion using a separate SOAP-based service
(Planned) JWT-based authentication

📦 Getting Started
Make sure your Spring Boot app is running locally on:
http://localhost:8080
All endpoints can be tested using Postman or any REST client. Please keep in mind that the database is not persistent, hence each time you restart the service, you would have to start from registering a user.

🧪 **How to Test in Postman**
1. Register a User
POST http://localhost:8080/users/register
Body (JSON):
{
  "username": "John",
  "email": "john@example.com",
  "password": "password123"
}
2. Add a Car
POST http://localhost:8080/cars
Body (JSON):
{
  "brand": "Toyota",
  "model": "Corolla",
  "licensePlate": "XYZ123",
  "year": 2020,
  "pricePerDayUsd": 45.0,
  "available": true
}
3. List All Cars
GET http://localhost:8080/cars
(this only works if there are cars added in the system)
4. List Available Cars
GET http://localhost:8080/cars/available
(this only works if there are cars added in the system)
5. Book a Car
POST http://localhost:8080/bookings/book
Body (JSON):
{
  "userId": 1,
  "carId": 1,
  "startDate": "2025-04-22",
  "endDate": "2025-04-24"
}
Response Example:
{
  "bookingId": 1,
  "userName": "john@example.com",
  "carModel": "Corolla",
  "carBrand": "Toyota",
  "licensePlate": "XYZ123",
  "startDate": "2025-04-22",
  "endDate": "2025-04-24",
  "totalCostUsd": 90.0
}
6. Return a Car
PUT http://localhost:8080/bookings/1/return

✅ Response: "Car returned successfully."

🛠 Tech Stack
Java 17+
Spring Boot
Spring Data JPA
H2 (in-memory) for testing
Postman for manual API testing


