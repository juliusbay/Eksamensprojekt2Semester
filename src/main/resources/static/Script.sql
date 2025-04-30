DROP DATABASE IF EXISTS Fleetmanager_db;
CREATE DATABASE Fleetmanager_db;
USE Fleetmanager_db;


CREATE TABLE users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       full_name VARCHAR(255) NOT NULL,
                       short_name VARCHAR(50),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('mekaniker', 'admin', 'regnskab') NOT NULL
);

CREATE TABLE car_model (
                           car_model_id INT PRIMARY KEY AUTO_INCREMENT,
                           model_year INT,
                           brand VARCHAR(100),
                           model VARCHAR(100),
                           car_emission INT,
                           car_equipment ENUM('La Prima', 'Sport', 'Advance', 'Performance', 'Rock', 'Techno', 'Icon', 'Long range', 'Varebil'),
                           steel_price INT,
                           registration_fee INT
);

CREATE TABLE bookings (
                          booking_id INT PRIMARY KEY AUTO_INCREMENT,
                          vehicle_id INT,
                          customer_name VARCHAR(255),
                          customer_email VARCHAR(255),
                          customer_phone VARCHAR(20),
                          lease_type ENUM('Limited', 'Unlimited'),
                          lease_duration INT,
                          lease_start_date DATE,
                          lease_end_date DATE,
                          booking_price DECIMAL(10,2),
                          advance_buyer BOOLEAN,
                          FOREIGN KEY (vehicle_id) REFERENCES rental_car(vehicle_id)
);

CREATE TABLE buyers (
                        buyer_id INT PRIMARY KEY AUTO_INCREMENT,
                        full_name VARCHAR(255) NOT NULL,
                        email VARCHAR(255),
                        phone_number VARCHAR(20),
                        is_pre_paid BOOLEAN,
                        booking_id INT,
                        FOREIGN KEY (booking_id) REFERENCES bookings(booking_id)
);

CREATE TABLE rental_car (
                            vehicle_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                            car_model_id INT,
                            vin_number VARCHAR(50) UNIQUE,
                            color VARCHAR(50),
                            return_address VARCHAR(255),
                            price DECIMAL(10,2),
                            mileage INT,
                            buyer_id INT,
                            booking_id INT,
                            status ENUM('klar', 'skadet', 'til_klargøring', 'udlejet') DEFAULT 'klar',
                            FOREIGN KEY (car_model_id) REFERENCES car_model(car_model_id),
                            FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
                            FOREIGN KEY (buyer_id) REFERENCES buyers(buyer_id)
);

CREATE TABLE damage_reports (
                                report_id INT PRIMARY KEY AUTO_INCREMENT,
                                vehicle_id INT,
                                report_date DATE,
                                damage_type VARCHAR(255),
                                damage_price DECIMAL(10,2),
                                handled_by INT,
                                FOREIGN KEY (vehicle_id) REFERENCES rental_car(vehicle_id),
                                FOREIGN KEY (handled_by) REFERENCES users(user_id)
);

CREATE TABLE vehicle_returns (
                                 return_id INT PRIMARY KEY AUTO_INCREMENT,
                                 vehicle_id INT,
                                 return_date DATE,
                                 returned_to ENUM('FDM', 'Bilabonnement'),
                                 report_id INT,
                                 FOREIGN KEY (vehicle_id) REFERENCES rental_car(vehicle_id),
                                 FOREIGN KEY (report_id) REFERENCES damage_reports(report_id)
);

/* Optional Table
CREATE TABLE transport_logs (
    transport_id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT,
    pickup_date DATE,
    delivery_date DATE,
    transport_duration INT,
    FOREIGN KEY (vehicle_id) REFERENCES rental_car(vehicle_id)
);
*/
