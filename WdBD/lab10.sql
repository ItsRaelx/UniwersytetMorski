CREATE TABLE Employees (
    employee_id SERIAL PRIMARY KEY,
    company_id INT,
    name VARCHAR,
    address VARCHAR,
    hourly_rate DOUBLE PRECISION,
    email VARCHAR,
    password_salt VARCHAR
);

CREATE TABLE Company (
    company_id SERIAL PRIMARY KEY,
    name VARCHAR,
    max_employees INT,
    price DOUBLE PRECISION
);

CREATE TABLE Permissions (
    employee_id INT,
    is_admin BOOLEAN,
    time_edit BOOLEAN,
    category_edit BOOLEAN,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);

CREATE TABLE Invoices (
    invoice_id SERIAL PRIMARY KEY,
    category_id INT,
    invoice_url VARCHAR,
    invoice_date DATE
);

CREATE TABLE Time_Records (
    record_id SERIAL PRIMARY KEY,
    employee_id INT,
    category_id INT,
    record_from DATE,
    record_to DATE,
    status VARCHAR,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);

CREATE TABLE Logs (
    log_id SERIAL PRIMARY KEY,
    employee_id INT,
    log_content VARCHAR,
    log_date DATE,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);

CREATE TABLE Leave_Records (
    record_id SERIAL PRIMARY KEY,
    employee_id INT,
    record_from DATE,
    record_to DATE,
    status VARCHAR,
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);

CREATE TABLE Categories (
    category_id SERIAL PRIMARY KEY,
    company_id INT,
    parent_category INT,
    name VARCHAR,
    FOREIGN KEY (company_id) REFERENCES Company(company_id),
    FOREIGN KEY (parent_category) REFERENCES Categories(category_id)
);

-- Employees
INSERT INTO Employees (company_id, name, address, hourly_rate, email, password_salt) 
VALUES 
(1, 'John Doe', '123 Main St', 20.50, 'john@example.com', 'abc123'),
(1, 'Jane Smith', '456 Elm St', 25.00, 'jane@example.com', 'xyz789'),
(2, 'Bob Johnson', '789 Oak St', 18.75, 'bob@example.com', 'def456');

-- Company
INSERT INTO Company (name, max_employees, price) 
VALUES 
('ABC Corp', 100, 5000.00),
('XYZ Inc', 50, 3000.00);

-- Permissions
INSERT INTO Permissions (employee_id, is_admin, time_edit, category_edit)
VALUES 
(1, true, true, true),
(2, false, true, false),
(3, false, true, true);

-- Invoices
INSERT INTO Invoices (category_id, invoice_url, invoice_date) 
VALUES 
(1, 'http://example.com/invoice1.pdf', '2024-05-01'),
(2, 'http://example.com/invoice2.pdf', '2024-05-05');

-- Time_Records
INSERT INTO Time_Records (employee_id, category_id, record_from, record_to, status)
VALUES 
(1, 1, '2024-05-01', '2024-05-02', 'approved'),
(2, 2, '2024-05-03', '2024-05-04', 'pending');

-- Logs
INSERT INTO Logs (employee_id, log_content, log_date) 
VALUES 
(1, 'Logged in', '2024-05-01'),
(2, 'Logged out', '2024-05-02');

-- Leave_Records
INSERT INTO Leave_Records (employee_id, record_from, record_to, status) 
VALUES 
(1, '2024-05-10', '2024-05-12', 'approved'),
(3, '2024-05-15', '2024-05-17', 'pending');

-- Categories
INSERT INTO Categories (company_id, parent_category, name) 
VALUES 
(1, NULL, 'Category A'),
(1, 1, 'Subcategory A1'),
(2, NULL, 'Category B');

-- Show data
SELECT * FROM Employees;
SELECT * FROM Company;
SELECT * FROM Permissions;
SELECT * FROM Invoices;
SELECT * FROM Time_Records;
SELECT * FROM Logs;
SELECT * FROM Leave_Records;
SELECT * FROM Categories;
