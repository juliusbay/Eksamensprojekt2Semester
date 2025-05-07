DROP DATABASE IF EXISTS Fleetmanager_db;
CREATE DATABASE Fleetmanager_db;
USE Fleetmanager_db;


CREATE TABLE employee (
                       employee_id INT PRIMARY KEY AUTO_INCREMENT,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       short_name VARCHAR(50),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('ADMIN', 'FORRETNINGSUDVIKLER', 'DATAREGISTRERING',
                           'MEKANIKER') NOT NULL
);

CREATE TABLE car_model (
                        car_model_id INT PRIMARY KEY AUTO_INCREMENT,
                        model_year INT,
                        brand VARCHAR(100),
                        model_name VARCHAR(100),
                        fuel_type ENUM ('El', 'Benzin', 'Hybrid'),
                        gear_box ENUM ('Automatisk', 'Manuel'),
                        car_emission INT,
                        car_equipment ENUM('La Prima', 'Sport', 'Advance', 'Performance', 'Rock', 'Techno', 'Icon', 'Long range', 'Varebil'),
                        steel_price INT
);

CREATE TABLE cars (
                            vehicle_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                            car_model_id INT,
                            vin_number VARCHAR(50) UNIQUE,
                            color VARCHAR(50),
                            return_address VARCHAR(255),
                            monthly_price DECIMAL(10,2),
                            status ENUM('klar', 'skadet', 'til_klargøring', 'udlejet') DEFAULT 'klar',
                            FOREIGN KEY (car_model_id) REFERENCES car_model(car_model_id)
);

CREATE TABLE chosen_choice(
                              choice_id INT PRIMARY KEY AUTO_INCREMENT,
                              clever_unlimited_network DOUBLE ,
                              clever_unlimited_hybrid DOUBLE,
                              clever_unlimited DOUBLE,
                              color_price DOUBLE,
                              viking_road_help DOUBLE,
                              green_tax DOUBLE,
                              low_self_insurance DOUBLE,
                              initial_payment DOUBLE,
                              monthly_kilometers DOUBLE,
                              drop_off_insurance DOUBLE,
                              tyre_rental DOUBLE

);


CREATE TABLE lease_agreement (
                        lease_agreement_id INT PRIMARY KEY AUTO_INCREMENT,
                        optional_id INT,
                        vehicle_id INT,
                        customer_id INT,
                        lease_type ENUM('Limited', 'Unlimited'),
                        lease_price INT,
                        lease_start_date DATE,
                        lease_end_date DATE,
                        initial_payment BOOLEAN,
                        return_location VARCHAR(255),
                        FOREIGN KEY (vehicle_id) REFERENCES cars(vehicle_id),
                        FOREIGN KEY (optional_id) REFERENCES chosen_choice(choice_id)
);

CREATE TABLE customers (
                        customer_id INT PRIMARY KEY AUTO_INCREMENT,
                        full_name VARCHAR(255) NOT NULL,
                        email VARCHAR(255),
                        phone_number VARCHAR(20),
                        address VARCHAR(100),
                        city VARCHAR(100),
                        postal_code INT,
                        cpr_number INT,
                        vehicle_id INT,
                        FOREIGN KEY (vehicle_id) REFERENCES cars(vehicle_id)
);

CREATE TABLE purchase_agreement(
                        purchase_agreement_id INT PRIMARY KEY AUTO_INCREMENT,
                        vehicle_id INT,
                        customer_id INT,
                        advance_buyer BOOLEAN,
                        car_price DOUBLE,
                        FOREIGN KEY (vehicle_id) REFERENCES cars(vehicle_id),
                        FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE damage(
                       damage_id INT PRIMARY KEY AUTO_INCREMENT,
                       vehicle_id INT,
                       damage_type VARCHAR(255),
                       damage_price DECIMAL(10,2),
                       damage_date DATE,
                       FOREIGN KEY (vehicle_id) REFERENCES cars(vehicle_id)
)


CREATE TABLE condition_report (
                        condition_report_id INT PRIMARY KEY AUTO_INCREMENT,
                        damage_id INT,
                        vehicle_id INT,
                        handled_by INT,
                        report_date DATE,
                        damage_type VARCHAR(255),
                        damage_price DECIMAL(10,2),
                        FOREIGN KEY (vehicle_id) REFERENCES cars(vehicle_id),
                        FOREIGN KEY (damage_id) REFERENCES damage(damage_id)
);


