-- Zadanie 1.
CREATE TABLE Invoices (
    InvoiceNumber INT PRIMARY KEY,
    SaleDate DATE NOT NULL
);

CREATE TABLE Products (
    ProductCode VARCHAR(255) PRIMARY KEY,
    ProductName TEXT NOT NULL,
    UnitPricePLN NUMERIC(10, 2) NOT NULL
);

CREATE TABLE Suppliers (
    SupplierCode INT PRIMARY KEY,
    SupplierName TEXT NOT NULL
);

CREATE TABLE SalesDetails (
    InvoiceNumber INT NOT NULL,
    ProductCode VARCHAR(255) NOT NULL,
    SupplierCode INT NOT NULL,
    Quantity INT NOT NULL,
    PRIMARY KEY (InvoiceNumber, ProductCode, SupplierCode),
    FOREIGN KEY (InvoiceNumber) REFERENCES Invoices(InvoiceNumber),
    FOREIGN KEY (ProductCode) REFERENCES Products(ProductCode),
    FOREIGN KEY (SupplierCode) REFERENCES Suppliers(SupplierCode)
);

INSERT INTO Invoices (InvoiceNumber, SaleDate) VALUES
(211347, '2004-01-15'),
(211348, '2004-01-16'),
(211349, '2004-01-17');

INSERT INTO Products (ProductCode, ProductName, UnitPricePLN) VALUES
('AA-E3235QW', 'DVD X12', 120.89),
('QD-30932X', 'LCD 27cali', 2100.00),
('RU-995748G', 'Nagrywarka CD 12', 230.56),
('GH-77835TU', 'Obudowa X45', 167.80);

INSERT INTO Suppliers (SupplierCode, SupplierName) VALUES
(211, 'NeverFail Inc.'),
(309, 'BeGood, Co.'),
(157, 'WasIsDas, GMBH');

INSERT INTO SalesDetails (InvoiceNumber, ProductCode, SupplierCode, Quantity) VALUES
(211347, 'AA-E3235QW', 211, 3),
(211347, 'QD-30932X', 211, 1),
(211347, 'RU-995748G', 309, 6),
(211348, 'AA-E3235QW', 211, 4),
(211349, 'GH-77835TU', 157, 3);

SELECT * FROM Invoices;
SELECT * FROM Products;
SELECT * FROM Suppliers;
SELECT * FROM SalesDetails;

-- Zad 1. 3NF
CREATE TABLE Invoices (
    InvoiceNumber INT PRIMARY KEY,
    SaleDate DATE NOT NULL
);

CREATE TABLE Products (
    ProductCode VARCHAR(255) PRIMARY KEY,
    ProductName TEXT NOT NULL,
    UnitPricePLN NUMERIC(10, 2) NOT NULL
);

CREATE TABLE Suppliers (
    SupplierCode INT PRIMARY KEY,
    SupplierName TEXT NOT NULL
);

CREATE TABLE ProductSuppliers (
    ProductCode VARCHAR(255) NOT NULL,
    SupplierCode INT NOT NULL,
    PRIMARY KEY (ProductCode, SupplierCode),
    FOREIGN KEY (ProductCode) REFERENCES Products(ProductCode),
    FOREIGN KEY (SupplierCode) REFERENCES Suppliers(SupplierCode)
);

CREATE TABLE SalesDetails (
    InvoiceNumber INT NOT NULL,
    ProductCode VARCHAR(255) NOT NULL,
    Quantity INT NOT NULL,
    PRIMARY KEY (InvoiceNumber, ProductCode),
    FOREIGN KEY (InvoiceNumber) REFERENCES Invoices(InvoiceNumber),
    FOREIGN KEY (ProductCode) REFERENCES Products(ProductCode)
);

-- Zad 2. 2NF

CREATE TABLE Students (
    Student_ID INT PRIMARY KEY,
    Last_Name TEXT NOT NULL,
    Department TEXT NOT NULL,
    Faculty_Code VARCHAR(255),
    Average_Grade NUMERIC(2, 2),
    FOREIGN KEY (Faculty_Code) REFERENCES Faculties(Faculty_Code)
);

CREATE TABLE Faculties (
    Faculty_Code VARCHAR(255) PRIMARY KEY,
    Faculty_Name TEXT NOT NULL,
    Phone_Number TEXT NOT NULL,
    School_Name TEXT NOT NULL
);

CREATE TABLE Supervisors (
    Supervisor_Last_Name TEXT PRIMARY KEY,
    Office_Location TEXT,
    Building TEXT,
    Phone_Number TEXT
);

CREATE TABLE Student_Supervisor (
    Student_ID INT,
    Supervisor_Last_Name TEXT,
    Hours INT,
    FOREIGN KEY (Student_ID) REFERENCES Students(Student_ID),
    FOREIGN KEY (Supervisor_Last_Name) REFERENCES Supervisors(Supervisor_Last_Name),
    PRIMARY KEY (Student_ID, Supervisor_Last_Name)
);

INSERT INTO Students (Student_ID, Last_Name, Department, Faculty_Code, Average_Grade) VALUES
(211343, 'Kowalski', 'Computer Science', 'WEIT', 3.45),
(200128, 'Nowak', 'Computer Science', 'WEIT', 4.12),
(199876, 'Zielony', 'Management', 'ZARZ', 3.87),
(223456, 'Głowacki', 'Mechanical Engineering', 'MECH', 3.12);

INSERT INTO Faculties (Faculty_Code, Faculty_Name, Phone_Number, School_Name) VALUES
('WEIT', 'Electrical Engineering', '3282678', 'Technical Sciences'),
('ZARZ', 'Management', '3282334', 'Mathematics'),
('MECH', 'Mechanical Engineering', '3282122', 'Technical Sciences');

INSERT INTO Supervisors (Supervisor_Last_Name, Office_Location, Building, Phone_Number) VALUES
('Wielek', 'T201', 'A-2', '234'),
('Horacy', 'J567', 'A-11', '456'),
('Walecki', 'K002', 'A-3', '890');

INSERT INTO Student_Supervisor (Student_ID, Supervisor_Last_Name, Hours) VALUES
(211343, 'Wielek', 75),
(200128, 'Wielek', 45),
(199876, 'Horacy', 32),
(223456, 'Walecki', 12);

-- Zad 2. 3NF

CREATE TABLE Students (
    Student_ID INT PRIMARY KEY,
    Last_Name TEXT NOT NULL,
    Department TEXT NOT NULL,
    Faculty_Code VARCHAR(255),
    Average_Grade NUMERIC(2, 2),
    FOREIGN KEY (Faculty_Code) REFERENCES Faculties(Faculty_Code)
);

CREATE TABLE Faculties (
    Faculty_Code VARCHAR(255) PRIMARY KEY,
    Faculty_Name TEXT NOT NULL,
    Phone_Number TEXT NOT NULL,
    School_Name TEXT NOT NULL
);

CREATE TABLE Supervisors (
    Supervisor_Last_Name TEXT PRIMARY KEY,
    Office_Location TEXT,
    Building TEXT,
    Phone_Number TEXT
);

CREATE TABLE Student_Faculty (
    Student_ID INT,
    Faculty_Code VARCHAR(255),
    PRIMARY KEY (Student_ID, Faculty_Code),
    FOREIGN KEY (Student_ID) REFERENCES Students(Student_ID),
    FOREIGN KEY (Faculty_Code) REFERENCES Faculties(Faculty_Code)
);

CREATE TABLE Student_Supervisor (
    Student_ID INT,
    Supervisor_Last_Name TEXT,
    Hours INT,
    PRIMARY KEY (Student_ID, Supervisor_Last_Name),
    FOREIGN KEY (Student_ID) REFERENCES Students(Student_ID),
    FOREIGN KEY (Supervisor_Last_Name) REFERENCES Supervisors(Supervisor_Last_Name)
);

INSERT INTO Students (Student_ID, Last_Name, Department, Average_Grade) VALUES
(211343, 'Kowalski', 'Computer Science', 3.45),
(200128, 'Nowak', 'Computer Science', 4.12),
(199876, 'Zielony', 'Management', 3.87),
(223456, 'Głowacki', 'Mechanical Engineering', 3.12);

INSERT INTO Faculties (Faculty_Code, Faculty_Name, Phone_Number, School_Name) VALUES
('WEIT', 'Electrical Engineering', '3282678', 'Technical Sciences'),
('ZARZ', 'Management', '3282334', 'Mathematics'),
('MECH', 'Mechanical Engineering', '3282122', 'Technical Sciences');

INSERT INTO Supervisors (Supervisor_Last_Name, Office_Location, Building, Phone_Number) VALUES
('Wielek', 'T201', 'A-2', '234'),
('Horacy', 'J567', 'A-11', '456'),
('Walecki', 'K002', 'A-3', '890');

INSERT INTO Student_Faculty (Student_ID, Faculty_Code) VALUES
(211343, 'WEIT'),
(200128, 'WEIT'),
(199876, 'ZARZ'),
(223456, 'MECH');

INSERT INTO Student_Supervisor (Student_ID, Supervisor_Last_Name, Hours) VALUES
(211343, 'Wielek', 75),
(200128, 'Wielek', 45),
(199876, 'Horacy', 32),
(223456, 'Walecki', 12);

-- Zad 3. 2NF
CREATE TABLE Departments (
    DepartmentCode VARCHAR(255) PRIMARY KEY,
    DepartmentName TEXT NOT NULL
);

CREATE TABLE Professors (
    EmployeeNumber INT PRIMARY KEY,
    LastName TEXT NOT NULL,
    ProfessorType TEXT NOT NULL,
    Office TEXT NOT NULL,
    DepartmentCode VARCHAR(255),
    FOREIGN KEY (DepartmentCode) REFERENCES Departments(DepartmentCode)
);

CREATE TABLE Students (
    StudentNumber INT PRIMARY KEY
);

CREATE TABLE ThesisAdvisors (
    StudentNumber INT,
    EmployeeNumber INT,
    PRIMARY KEY (StudentNumber, EmployeeNumber),
    FOREIGN KEY (StudentNumber) REFERENCES Students(StudentNumber),
    FOREIGN KEY (EmployeeNumber) REFERENCES Professors(EmployeeNumber)
);

CREATE TABLE Committees (
    CommitteeCode VARCHAR(255),
    EmployeeNumber INT,
    PRIMARY KEY (CommitteeCode, EmployeeNumber),
    FOREIGN KEY (EmployeeNumber) REFERENCES Professors(EmployeeNumber)
);

CREATE TABLE Journals (
    JournalCode VARCHAR(255),
    EmployeeNumber INT,
    PRIMARY KEY (JournalCode, EmployeeNumber),
    FOREIGN KEY (EmployeeNumber) REFERENCES Professors(EmployeeNumber)
);

INSERT INTO Departments (DepartmentCode, DepartmentName)
VALUES 
    ('MECH', 'Mechaniczny'),
    ('ART', 'Artystyczny'),
    ('WEIT', 'Informatyczny');

INSERT INTO Professors (EmployeeNumber, LastName, ProfessorType, Office, DepartmentCode)
VALUES 
    (123, 'Nowak', 'Profesor zw.', 'A2-422B', 'WEIT'),
    (104, 'Kowalski', 'Profesor nad.', 'A1-13A', 'MECH'),
    (118, 'Czarny', 'Profesor bel.', 'A2-321', 'WEIT'),
    (-1, 'Nieznany', 'Brak danych', 'Brak danych', NULL);

INSERT INTO Students (StudentNumber)
VALUES 
    (1215),
    (2312),
    (3233),
    (2218),
    (0045),
    (3456),
    (5689),
    (6543),
    (2333),
    (4535),
    (7899),
    (3455);

INSERT INTO ThesisAdvisors (StudentNumber, EmployeeNumber)
VALUES 
    (1215, 123),
    (2312, 123),
    (3233, 123),
    (2218, 123),
    (0045, 104),
    (3456, 104),
    (5689, 118),
    (6543, 118),
    (2333, 118),
    (4535, -1),
    (7899, -1),
    (3455, -1);

INSERT INTO Committees (CommitteeCode, EmployeeNumber)
VALUES 
    ('DOKT', 123),
    ('MAGIS', 123),
    ('INZY', 123),
    ('PRAC', 104),
    ('INZY', 104),
    ('MAGIS', 118),
    ('INZY', 118),
    ('SOCJA', -1),
    ('DOKT', -1);

INSERT INTO Journals (JournalCode, EmployeeNumber)
VALUES 
    ('IJC', 123),
    ('IEEE', 123),
    ('CAS', 118),
    ('AJC', 118),
    ('CAS', -1),
    ('IEEE', -1),
    ('IEE', -1);

-- Zad 3. 3NF

CREATE TABLE Departments (
    DepartmentCode VARCHAR(255) PRIMARY KEY,
    DepartmentName TEXT NOT NULL
);

CREATE TABLE Professors (
    EmployeeNumber INT PRIMARY KEY,
    LastName TEXT NOT NULL,
    ProfessorType TEXT NOT NULL,
    Office TEXT NOT NULL
);

CREATE TABLE DepartmentMemberships (
    EmployeeNumber INT,
    DepartmentCode VARCHAR(255),
    PRIMARY KEY (EmployeeNumber, DepartmentCode),
    FOREIGN KEY (EmployeeNumber) REFERENCES Professors(EmployeeNumber),
    FOREIGN KEY (DepartmentCode) REFERENCES Departments(DepartmentCode)
);

CREATE TABLE Students (
    StudentNumber INT PRIMARY KEY
);

CREATE TABLE ThesisAdvisors (
    StudentNumber INT,
    EmployeeNumber INT,
    PRIMARY KEY (StudentNumber, EmployeeNumber),
    FOREIGN KEY (StudentNumber) REFERENCES Students(StudentNumber),
    FOREIGN KEY (EmployeeNumber) REFERENCES Professors(EmployeeNumber)
);

CREATE TABLE Committees (
    CommitteeCode VARCHAR(255) PRIMARY KEY
);

CREATE TABLE CommitteeMemberships (
    CommitteeCode VARCHAR(255),
    EmployeeNumber INT,
    PRIMARY KEY (CommitteeCode, EmployeeNumber),
    FOREIGN KEY (CommitteeCode) REFERENCES Committees(CommitteeCode),
    FOREIGN KEY (EmployeeNumber) REFERENCES Professors(EmployeeNumber)
);

CREATE TABLE Journals (
    JournalCode VARCHAR(255) PRIMARY KEY
);

CREATE TABLE JournalEditors (
    JournalCode VARCHAR(255),
    EmployeeNumber INT,
    PRIMARY KEY (JournalCode, EmployeeNumber),
    FOREIGN KEY (JournalCode) REFERENCES Journals(JournalCode),
    FOREIGN KEY (EmployeeNumber) REFERENCES Professors(EmployeeNumber)
);

INSERT INTO Departments (DepartmentCode, DepartmentName)
VALUES 
    ('MECH', 'Mechaniczny'), 
    ('ART', 'Artystyczny'), 
    ('WEIT', 'Informatyczny');

INSERT INTO Professors (EmployeeNumber, LastName, ProfessorType, Office)
VALUES 
    (123, 'Nowak', 'Profesor zw.', 'A2-422B'), 
    (104, 'Kowalski', 'Profesor nad.', 'A1-13A'), 
    (118, 'Czarny', 'Profesor bel.', 'A2-321'), 

INSERT INTO DepartmentMemberships (EmployeeNumber, DepartmentCode)
VALUES 
    (123, 'WEIT'), 
    (104, 'MECH'), 
    (118, 'WEIT'), 
    (-1, NULL);

INSERT INTO Students (StudentNumber)
VALUES 
    (1215), 
    (2312), 
    (3233), 
    (2218), 
    (0045), 
    (3456), 
    (5689), 
    (6543), 
    (2333), 
    (4535), 
    (7899), 
    (3455);

INSERT INTO ThesisAdvisors (StudentNumber, EmployeeNumber)
VALUES 
    (1215, 123), 
    (2312, 123), 
    (3233, 123), 
    (2218, 123), 
    (0045, 104), 
    (3456, 104), 
    (5689, 118), 
    (6543, 118), 
    (2333, 118), 
    (4535, -1), 
    (7899, -1), 
    (3455, -1);

INSERT INTO Committees (CommitteeCode)
VALUES 
    ('DOKT'), 
    ('MAGIS'), 
    ('INZY'), 
    ('PRAC'), 
    ('SOCJA');

INSERT INTO CommitteeMemberships (CommitteeCode, EmployeeNumber)
VALUES 
    ('DOKT', 123), 
    ('MAGIS', 123), 
    ('INZY', 123), 
    ('PRAC', 104), 
    ('INZY', 104), 
    ('MAGIS', 118), 
    ('INZY', 118), 
    ('SOCJA', -1), 
    ('DOKT', -1);

INSERT INTO Journals (JournalCode)
VALUES 
    ('IJC'), 
    ('IEEE'), 
   ('CAS'),  
   ('AJC'),  
   ('IEE');

INSERT INTO JournalEditors (JournalCode, EmployeeNumber)
VALUES  
   ('IJC', 123),  
   ('IEEE', 123),  
   ('CAS', 118),  
   ('AJC', 118),  
   ('CAS', -1),  
   ('IEEE', -1),  
   ('IEE', -1);
