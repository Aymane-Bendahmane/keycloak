# Token Relay Pattern with Keycloak as OAuth2 Server

## Overview

The **Token Relay Pattern** is a design approach used to forward or inject OAuth2 tokens between microservices in a secure and seamless manner. In this pattern, when a client authenticates and acquires an OAuth2 access token (e.g., via Keycloak), this token is forwarded through a series of microservices to ensure that the client's identity and permissions are preserved across service boundaries.

Keycloak is used here as the **OAuth2** server to manage authentication and authorization in a centralized way.

## Key Concepts

### 1. OAuth2 Access Token
- An OAuth2 access token is issued by the authorization server (Keycloak) after a client successfully authenticates.
- This token is used to access protected resources and verify the user's identity.

### 2. Token Relay
- The concept involves "relaying" the OAuth2 access token from one service to another in order to maintain the authentication context and permissions.
- The access token is typically passed along as a bearer token in the `Authorization` header of HTTP requests.

### 3. Keycloak as OAuth2 Server
- **Keycloak** is an open-source identity and access management system that supports OAuth2 and OpenID Connect.
- In this setup, Keycloak acts as the **Authorization Server** for issuing and validating OAuth2 tokens.

## How the Token Relay Pattern Works

1. **Client Authentication:**
    - The client (e.g., a web application or mobile app) authenticates with Keycloak and receives an OAuth2 access token.

2. **Service 1 (Microservice A):**
    - The client then sends a request to the first microservice (Service 1), including the OAuth2 token in the `Authorization` header.
    - Service 1 verifies the token by validating it with Keycloak (or using Keycloak's adapter).

3. **Service 2 (Microservice B):**
    - Service 1, after completing its logic, needs to make a request to another microservice (Service 2) on behalf of the client.
    - Instead of requiring the client to authenticate again, Service 1 relays the token to Service 2, which includes it in the `Authorization` header.

4. **Service 2 Validation:**
    - Service 2 validates the access token using Keycloak and performs the necessary action based on the token's validity and the user’s permissions.

5. **Token Propagation:**
    - Throughout the chain of services, each microservice forwards the token, ensuring that the client’s identity and authorization context are preserved.

## Example Flow

1. **Client** authenticates with Keycloak and gets an access token.
2. **Service 1** receives the token and uses it to make requests to **Service 2**.
3. **Service 2** validates the token using Keycloak and performs actions on behalf of the client.

## Setting Up Keycloak as OAuth2 Server

### 1. Install Keycloak

Follow the official [Keycloak installation guide](https://www.keycloak.org/docs/latest/server_installation/) to install and configure Keycloak.

### 2. Create a Keycloak Client

1. Log in to the Keycloak Admin Console.
2. Go to the **Clients** section and create a new client.
3. Set the client protocol to **OpenID Connect**.
4. Configure the **Access Type** to **Confidential**.
5. Set up the **Valid Redirect URIs** and **Web Origins** to match your services.

### 3. Configure OAuth2 in Microservices

Both **Service 1** and **Service 2** should be configured to use Keycloak for OAuth2 validation:

#### Service 1 Configuration:
- In the `application.properties` (or `application.yml`) file of Service 1:
    ```properties
    keycloak.auth-server-url=http://localhost:8080/auth
    keycloak.realm=example-realm
    keycloak.resource=service1-client
    keycloak.public-client=true
    keycloak.credentials.secret=secret
    ```

#### Service 2 Configuration:
- In the `application.properties` (or `application.yml`) file of Service 2:
    ```properties
    keycloak.auth-server-url=http://localhost:8080/auth
    keycloak.realm=example-realm
    keycloak.resource=service2-client
    keycloak.public-client=true
    keycloak.credentials.secret=secret
    ```

### 4. Secure Your Microservices

Ensure that each microservice uses Keycloak’s OAuth2 token validation to secure their endpoints. You can configure **Spring Security** or other frameworks to validate tokens with Keycloak's public key.

## Benefits of the Token Relay Pattern

- **Centralized Authentication:** Keycloak handles authentication, so you don't need to reimplement security logic in each microservice.
- **Decoupling:** Microservices are decoupled from authentication logic and can focus on business logic.
- **Security:** By forwarding tokens, services don't have to share sensitive user information directly. Instead, the token acts as a secure reference.
- **Scalability:** This pattern scales well in microservice architectures as the authorization logic is centralized in Keycloak.

## Challenges

- **Token Expiry:** OAuth2 access tokens have a limited lifespan. Ensure that token renewal and refreshing are properly handled to prevent unauthorized requests.
- **Token Size:** OAuth2 tokens, especially JWT tokens, can become large if they contain a lot of claims. This could cause overhead in service-to-service communication.
- **Security:** Ensure that tokens are transmitted securely (via HTTPS) to prevent interception.

## Conclusion

The **Token Relay Pattern** is an effective solution for propagating authentication and authorization information across microservices. By using Keycloak as the OAuth2 server, you can centralize security and ensure that client identity and permissions are consistently maintained across all microservices in your architecture.

For more information on setting up Keycloak, visit the [Keycloak Documentation](https://www.keycloak.org/docs/latest/).
