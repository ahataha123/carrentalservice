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
**POST** http://localhost:8080/v1/users
Body (JSON):
{
  "username": "John",
  "email": "john@example.com",
  "password": "password123"
}
2. 🔓 Login
POST http://localhost:8080/v1/auth/login
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
**POST** http://localhost:8080/v1/cars
Body (JSON) (for part1 "year", for part2 "manufactureYear":
{
  "brand": "Toyota",
  "model": "Corolla",
  "licensePlate": "XYZ123",
  "manufactureYear": 2020,
  "pricePerDayUsd": 45.0,
  "available": true
}
4. 📃 List All Cars
**GET** http://localhost:8080/v1/cars
🔹 Returns all cars (available and booked).

5. ✅ List Available Cars
**GET** http://localhost:8080/v1/cars/available
🔹 Returns only cars that are currently available.

6. 📅 Book a Car
**POST** http://localhost:8080/v1/bookings
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
**PATCH** http://localhost:8080/v1/bookings/1/return
✅ Response:
"Car returned successfully."

**Google Maps Setup**
This project integrates the Google Maps Distance Matrix API to calculate distances between two locations.

To enable this feature, you need to add your API key in a local application.properties file:

**google.maps.api.key=YOUR_GOOGLE_MAPS_API_KEY**

📝 The application.properties file is not committed to version control for security reasons.

8. 📏 Get Distance Between Two Locations (Web Service 3)
GET http://localhost:8080/v1/distance?origin=Vienna&destination=Graz
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

