CREATE TABLE Medicine (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(10) UNIQUE,
    medicine_name VARCHAR(255) NOT NULL,
    medicine_type VARCHAR(255),
    price DECIMAL(10, 2),
    date_Of_Birth DATE
   
);
