# UrbanHomes

AoristHomes is a Spring Boot application that provides a platform for managing properties, user investments, transactions, and more. This application uses MongoDB as its database.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage properties
- Manage user investments
- Manage transactions
- Manage user wallets
- Manage user cards

## Requirements

- Java 17
- Maven
- MongoDB

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/AoristHomes.git
    cd AoristHomes
    ```

2. Install dependencies:
    ```sh
    ./mvnw install
    ```

3. Configure MongoDB connection in [application.yml](http://_vscodecontentref_/0):
    ```yaml
    spring:
      data:
        mongodb:
          uri: mongodb://localhost:27017/AoristHomes?authSource=admin
    ```

4. Run the application:
    ```sh
    ./mvnw spring-boot:run
    ```

## Usage

The application will be available at [http://localhost:8080](http://_vscodecontentref_/1).

## API Endpoints

### Property Endpoints

- **Add Property**
    ```http
    POST /api/properties
    ```
    Request Body:
    ```json
    {
        "name": "Property Name",
        "description": "Property Description",
        "location": "Property Location",
        "price": 100000,
        "coverPhoto": "coverPhotoUrl",
        "coverVideo": ["videoUrl1", "videoUrl2"],
        "panoramas": ["panoramaUrl1", "panoramaUrl2"],
        "title": "Property Title",
        "area": 100,
        "bedrooms": 3,
        "bathrooms": 2,
        "photos": ["photoUrl1", "photoUrl2"],
        "amenities": ["amenity1", "amenity2"],
        "furnishingStatus": true,
        "availability": true,
        "totalInvested": 50000,
        "agencyId": "agencyId",
        "availableForInvestment": ["investmentOption1", "investmentOption2"]
    }
    ```

- **Get Property by ID**
    ```http
    GET /api/properties/{id}
    ```

- **Update Property**
    ```http
    PUT /api/properties/{id}
    ```
    Request Body: Same as Add Property

- **Delete Property**
    ```http
    DELETE /api/properties/{id}
    ```

### User Investment Endpoints

- **Add User Investment**
    ```http
    POST /api/user-investments
    ```
    Request Body:
    ```json
    {
        "userId": "userId",
        "propertyId": "propertyId",
        "investmentAmount": 10000
    }
    ```

- **Get User Investment by ID**
    ```http
    GET /api/user-investments/{id}
    ```

- **Get Investments by User ID**
    ```http
    GET /api/user-investments/user/{userId}
    ```

- **Update Investment Amount**
    ```http
    PUT /api/user-investments/{id}/amount
    ```
    Request Body:
    ```json
    15000
    ```

- **Delete Investment**
    ```http
    DELETE /api/user-investments/{id}
    ```

### Wallet Endpoints

- **Deposit Funds**
    ```http
    POST /api/wallets/deposit
    ```
    Request Parameters:
    - [userId](http://_vscodecontentref_/2): User ID
    - [amount](http://_vscodecontentref_/3): Amount to deposit

- **Withdraw Funds**
    ```http
    POST /api/wallets/withdraw
    ```
    Request Parameters:
    - [userId](http://_vscodecontentref_/4): User ID
    - [amount](http://_vscodecontentref_/5): Amount to withdraw

- **Get Wallet by ID**
    ```http
    GET /api/wallets/{id}
    ```

### Card Endpoints

- **Add Card**
    ```http
    POST /api/cards/add
    ```
    Request Body:
    ```json
    {
        "userId": "userId",
        "cardNumber": "1234567890123456",
        "cardHolderName": "Card Holder",
        "expiryDate": "12/23",
        "cvv": "123"
    }
    ```

- **Get Card by User ID**
    ```http
    GET /api/cards/user/{userId}
    ```

- **Update Card**

    ```http
    PUT /api/cards/{id}
    ```
    Request Body: Same as Add Card

- **Delete Card**
    ```http
    DELETE /api/cards/{id}
    ```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request.

## License

This project is licensed under the Apache License 2.0. See the LICENSE file for details.
