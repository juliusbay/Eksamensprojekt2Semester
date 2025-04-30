DROP DATABASE IF EXISTS Fleetmanager_db;
CREATE DATABASE Fleetmanager_db;
USE Fleetmanager_db;


CREATE TABLE Users (
                       userID INT PRIMARY KEY AUTO_INCREMENT,
                       fullName VARCHAR(255),
                       shortName VARCHAR(50),
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       role ENUM('mekaniker', 'admin', 'regnskab') NOT NULL
);

CREATE TABLE Vehicles (
                        vehicleID INT PRIMARY KEY AUTO_INCREMENT,
                        vinNumber VARCHAR(50) UNIQUE,
                        year INT,
                        brand VARCHAR(100),
                        model VARCHAR(100),
                        color VARCHAR(50),
                        carEmission DECIMAL(5,2),
                        returnAddress VARCHAR(255),
                        price DECIMAL(10,2),
                        mileage INT,
                        status ENUM('klar', 'skadet', 'til_klargøring', 'udlejet') DEFAULT 'klar'
);
CREATE TABLE Bookings (
                        bookingID INT PRIMARY KEY AUTO_INCREMENT,
                        vehicleID INT,
                        customerName VARCHAR(255),
                        customerEmail VARCHAR(255),
                        customerPhone VARCHAR(20),
                        leaseType ENUM('Limited', 'Unlimited'),
                        leaseDuration INT,
                        leaseStartDate DATE,
                        leaseEndDate DATE,
                        bookingPrice DECIMAL(10,2),
                        advanceBuyer BOOLEAN,
                        FOREIGN KEY (vehicleID) REFERENCES Vehicles(vehicleID)
);
CREATE TABLE DamageReports (
                        reportID INT PRIMARY KEY AUTO_INCREMENT,
                        vehicleID INT,
                        reportDate DATE,
                        damageType VARCHAR(255),
                        damagePrice DECIMAL(10,2),
                        handledBy INT,
                        FOREIGN KEY (vehicleID) REFERENCES Vehicles(vehicleID),
                        FOREIGN KEY (handledBy) REFERENCES Users(userID)
);
CREATE TABLE Buyers (
                        buyerID INT PRIMARY KEY AUTO_INCREMENT,
                        fullName VARCHAR(255),
                        email VARCHAR(255),
                        phoneNumber VARCHAR(20),
                        isPrePaid BOOLEAN,
                        bookingID INT,
                        FOREIGN KEY (bookingID) REFERENCES Bookings(bookingID)
);


CREATE TABLE VehicleReturns (
                        returnID INT PRIMARY KEY AUTO_INCREMENT,
                        vehicleID INT,
                        returnDate DATE,
                        returnedTo ENUM('FDM', 'Bilabonnement'),
                        reportID INT,
                        FOREIGN KEY (vehicleID) REFERENCES Vehicles(vehicleID),
                        FOREIGN KEY (reportID) REFERENCES DamageReports(reportID)
);
/*
CREATE TABLE TransportLogs (
                        transportID INT PRIMARY KEY AUTO_INCREMENT,
                        vehicleID INT,
                        pickupDate DATE,
                        deliveryDate DATE,
                        transportDuration INT,
                        FOREIGN KEY (vehicleID) REFERENCES Vehicles(vehicleID)
);
 */