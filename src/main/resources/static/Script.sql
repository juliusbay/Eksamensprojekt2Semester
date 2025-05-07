DROP DATABASE IF EXISTS fleetmanager_db;
CREATE DATABASE fleetmanager_db;
USE fleetmanager_db;


CREATE TABLE employee (
                          employee_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50) NOT NULL,
                          short_name VARCHAR(5),
                          email VARCHAR(255) UNIQUE NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          role ENUM('ADMIN', 'FORRETNINGSUDVIKLER', 'DATAREGISTRERING', 'MEKANIKER') NOT NULL
);

INSERT INTO employee (employee_id, first_name, last_name, short_name, email, password, role) VALUES
                                                (1, 'Demo', 'Demo', 'demo', 'demo@demo.demo', 'demo', 'ADMIN');

CREATE TABLE car_model (
                           car_model_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                           model_name VARCHAR(100),
                           brand VARCHAR(100),
                           fuel_type ENUM ('ELECTRIC', 'GASOLINE', 'HYBRID'),
                           model_year INT,
                           gear_box ENUM ('AUTOMATIC', 'MANUAL'),
                           car_emission INT,
                           car_equipment VARCHAR(100),
                           steel_price DOUBLE(10, 2)
);

INSERT INTO car_model (model_name, brand, fuel_type, model_year, gear_box, car_emission, car_equipment, steel_price)
VALUES ('Model X', 'Tesla', 'ELECTRIC', 2024, 'AUTOMATIC', 0, 'Performance', 750000);


CREATE TABLE car (
                     vehicle_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                     fk_car_model_id INT,
                     vin_number VARCHAR(50) UNIQUE,
                     color VARCHAR(50),
                     monthly_price DECIMAL(10,2),
                     bought BOOLEAN,
                     status ENUM('READY', 'DAMAGED', 'GETTING_REPAIRED', 'RENTED') DEFAULT 'READY',
                     FOREIGN KEY (fk_car_model_id) REFERENCES car_model(car_model_id)
);
INSERT INTO car (fk_car_model_id, vin_number, color, monthly_price, bought, status)
VALUES
    (1, '1HGBH41JXMN109186', 'Red', 199.99, TRUE, 'READY');


CREATE TABLE customer (
                          customer_id INT PRIMARY KEY AUTO_INCREMENT,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50),
                          email VARCHAR(255),
                          phone_number VARCHAR(20),
                          address VARCHAR(100),
                          city VARCHAR(100),
                          postal_code INT,
                          cpr_number INT,
                          fk_vehicle_id INT,
                          FOREIGN KEY (fk_vehicle_id) REFERENCES cars(vehicle_id)
);

CREATE TABLE lease_agreement (
                                 lease_agreement_id INT PRIMARY KEY AUTO_INCREMENT,
                                 fk_vehicle_id INT,
                                 fk_customer_id INT,
                                 fk_chosen_choice INT,
                                 lease_type ENUM('LIMITED', 'UNLIMITED'),
                                 lease_start_date DATE,
                                 lease_end_date DATE,
                                 lease_price DOUBLE,
                                 return_location VARCHAR(255),
                                 FOREIGN KEY (fk_vehicle_id) REFERENCES cars(vehicle_id),
                                 FOREIGN KEY (fk_customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE purchase_agreement(
                                   purchase_agreement_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                                   fk_vehicle_id INT,
                                   fk_customer_id INT,
                                   paid BOOLEAN,
                                   car_price DOUBLE(10, 2),
                                   FOREIGN KEY (fk_vehicle_id) REFERENCES cars(vehicle_id),
                                   FOREIGN KEY (fk_customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE choice(
                       choice_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                       choice_name VARCHAR(50),
                       choice_price DOUBLE(10, 2)
);

CREATE TABLE chosen_choice(
                              fk_choice_id INT,
                              fk_lease_agreement_id INT,
                              FOREIGN KEY (fk_choice_id) REFERENCES choice(choice_id),
                              FOREIGN KEY (fk_lease_agreement_id) REFERENCES lease_agreement(lease_agreement_id)
);

CREATE TABLE available_choice(
                                 fk_car_model_id INT,
                                 fk_choice_id INT,
                                 FOREIGN KEY (fk_car_model_id) REFERENCES car_model(car_model_id),
                                 FOREIGN KEY (fk_choice_id) REFERENCES choice(choice_id)
);

CREATE TABLE damage(
                       damage_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE ,
                       fk_vehicle_id INT,
                       damage_type VARCHAR(255),
                       damage_price DECIMAL(10,2),
                       damage_date DATE,
                       FOREIGN KEY (fk_vehicle_id) REFERENCES cars(vehicle_id)
);

CREATE TABLE condition_report (
                                  condition_report_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE ,
                                  fk_damage_id INT,
                                  fk_vehicle_id INT,
                                  handled_by VARCHAR(50),
                                  report_date DATE,
                                  FOREIGN KEY (fk_vehicle_id) REFERENCES cars(vehicle_id),
                                  FOREIGN KEY (fk_damage_id) REFERENCES damage(damage_id)
);



