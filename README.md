# Useless Facts API

This service fetches random facts from an external API, caches them, and provides a shortened link
to each fact.

It also tracks accesses to each shortened link, gathering statistics on link usage.

## Prerequisites

To run this service, Docker must be installed on your system.

## Installation Instructions

1. **Clone this repository:**

   ```bash
   git clone https://github.com/DisterDE/useless-facts-api.git
   cd useless-facts-api
   ```

2. **Build the Docker image:**

   ```bash
   docker build -t useless-facts-api .
   ```

3. **Run the Docker container:**

   ```bash
   docker run -p 8080:8080 useless-facts-api
   ```

After this, the service will be accessible at [http://localhost:8080](http://localhost:8080).

## Interacting with the Service

To interact with the service, you can use tools like Postman, cURL, or other HTTP clients.

Additionally, Swagger-UI is available
at [http://localhost:8080/swagger](http://localhost:8080/swagger) for easy API exploration.

**Warning:** Be attentive, as the API returns the full endpoint path as shortened links.

When making API calls via HTTP clients,
the full path should be specified immediately after the host.
However, in Swagger, the path is predefined,
so you only need to insert the unique link identifier, not the entire shortened URL.

## Authorization

To access statistics, authorization is required with the credentials `admin-admin`.

## Endpoints

- `GET /facts` - Returns all previously cached facts.
- `POST /facts` - Fetches a random fact and returns a shortened URL for future access to that fact.
- `GET /facts/{shortenedUrl}` - Retrieves a cached fact using its shortened URL.
- `GET /facts/{shortenedUrl}/redirect` - Redirects the request to the original external service,
  returning the complete response from the external facts service.
- `GET /admin/statistics` - Returns a list of shortened URLs along with the count of accesses for
  each link. Requires authorization.

## External Service

Facts are retrieved from the following external
service: [uselessfacts.jsph.pl](https://uselessfacts.jsph.pl)