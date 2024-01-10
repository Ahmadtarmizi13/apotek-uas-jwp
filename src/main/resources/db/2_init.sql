CREATE TABLE Customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(10) UNIQUE,
    customer_name VARCHAR(50),
    phone_number VARCHAR(255),
    address TEXT,
    date_of_birth DATE,
    );
