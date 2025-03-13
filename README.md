# Spring Boot Guestbook Application

This is a Spring Boot version of the Kubernetes Guestbook application.

## Requirements

- Java 17
- Maven 3.6+
- Redis (for production use)

## Running Locally

### Without Docker

1. Clone this repository
2. Run the application:
   ```
   mvn spring-boot:run
   ```
3. Access the application at http://localhost:3000

### With Docker

1. Build the Docker image:
   ```
   docker build -t guestbook:v1 .
   ```

2. Run the container:
   ```
   docker run -p 3000:3000 guestbook:v1
   ```

3. Access the application at http://localhost:3000

## Deploying to Kubernetes

1. Update the `deployment.yml` file with your namespace:
   ```
   image: us.icr.io/${YOUR_NAMESPACE}/guestbook:v1
   ```

2. Apply the deployment:
   ```
   kubectl apply -f deployment.yml
   ```

## Application Structure

- `GuestbookApplication.java` - Main Spring Boot application
- `GuestbookController.java` - REST endpoints for the guestbook functionality
- `RedisConfig.java` - Redis configuration for data storage
- Static files in `src/main/resources/static`:
  - `index.html` - Main UI
  - `script.js` - Client-side JavaScript
  - `style.css` - Styling

## Features

- View guestbook entries
- Add new entries to the guestbook
- Store entries in Redis
- View environment variables
- Check Redis connection status

## Redis Configuration

Redis connection details can be configured in `application.properties` or via environment variables:
- `REDIS_MASTER_SERVICE_HOST` - Redis host
- `REDIS_MASTER_SERVICE_PORT` - Redis port
- `REDIS_MASTER_SERVICE_PASSWORD` - Redis password (if required)