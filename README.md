🚗 Car Rental Service

A simple RESTful Car Rental Service built with Spring Boot, offering the following core features:

✅ User registration

✅ List all cars

✅ Book and return a car

✅ View booked and available cars

🔄 (Planned) Prices stored in USD

💱 (Planned) Currency conversion using SOAP service

🔐 (Planned) JWT-based authentication

📦 **Getting Started**
Your Spring Boot app should be running locally at by default:
**http://localhost:8080**
⚠️ Note:
The app uses an in-memory H2 database. All data will be lost on server restart. Please re-register users and cars before testing.
🧪 **How to Test in Postman**
1. 👤 Register a User
**POST** http://localhost:8080/users/register
Body (JSON):
{
  "name": "John",
  "email": "john@example.com",
  "password": "password123"
}
2. 🔓 Login
POST http://localhost:8080/auth/login
Request Body:
{
  "email": "john@example.com",
  "password": "password123"
}
Response Example:
{
  "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
Add the token to Postman for testing purposes
3. 🚘 Add a Car
**POST** http://localhost:8080/cars
Body (JSON):
{
  "brand": "Toyota",
  "model": "Corolla",
  "licensePlate": "XYZ123",
  "year": 2020,
  "pricePerDayUsd": 45.0,
  "available": true
}
4. 📃 List All Cars
**GET** http://localhost:8080/cars
🔹 Returns all cars (available and booked).

5. ✅ List Available Cars
**GET** http://localhost:8080/cars/available
🔹 Returns only cars that are currently available.

6. 📅 Book a Car
**POST** http://localhost:8080/bookings/book
Body (JSON):
{
  "userId": 1,
  "carId": 1,
  "startDate": "2025-04-22",
  "endDate": "2025-04-24"
}
Sample Response:
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

7. 🔁 Return a Car
**PUT** http://localhost:8080/bookings/1/return
✅ Response:
"Car returned successfully."

8. 📏 Get Distance Between Two Locations (Web Service 3)
GET http://localhost:8080/distance?origin=Vienna&destination=Graz
Headers: Authorization: Bearer <your_token>

Response Example:
{
    "distanceText": "197 km",
    "durationText": "2 hours 15 mins"
}

🛠 Tech Stack
☕ Java 17+

🚀 Spring Boot

🗃 Spring Data JPA

🧪 H2 (in-memory DB)

📬 Postman (for manual API testing)

