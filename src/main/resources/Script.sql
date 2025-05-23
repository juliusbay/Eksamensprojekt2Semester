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
                          role ENUM('ADMIN', 'BUSINESS_DEVELOPER', 'DATA_RECORDING', 'MECHANIC') NOT NULL
);


INSERT INTO employee (employee_id, first_name, last_name, short_name, email, password, role) VALUES
                                                (1, 'Demo', 'Demo', 'demo', 'demo@demo.demo', 'demo', 'ADMIN'),
                                                (2, 'Jürgen', 'Hinterseer', 'JÜHIN', 'Jürgen@email.dk', 'Lagkage', 'DATA_RECORDING'),
                                                (3, 'demo', 'forretningsudvikler', 'demof', 'forretning@email.dk', '1234', 'BUSINESS_DEVELOPER'),
                                                (4, 'demo', 'mekaniker', 'demom', 'mekaniker@email.dk', '1234', 'MECHANIC');

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
VALUES ('Model X', 'Tesla', 'ELECTRIC', 2024, 'AUTOMATIC', 0, 'Performance', 750000),
       ('Ariya', 'Nissan', 'ELECTRIC', 2025, 'AUTOMATIC', 0, 'Evolve', 550000),
       ('500', 'Fiat', 'GASOLINE', 2025, 'MANUAL', 105, 'Vita Comfort', 250000),
       ('500e', 'Fiat', 'ELECTRIC', 2025, 'AUTOMATIC', 0, 'La Prima', 250000),
       ('500e', 'Fiat', 'ELECTRIC', 2025, 'AUTOMATIC', null, 'Icon', 250000),
       ('Malibu', 'Chevrolet', 'GASOLINE', 2024, 'AUTOMATIC', 135, 'LT', 320000),
       ('Golf', 'Volkswagen', 'GASOLINE', 2025, 'MANUAL', 120, 'Comfortline', 300000),
       ('Accord Hybrid', 'Honda', 'HYBRID', 2023, 'AUTOMATIC', 90, 'EX-L', 380000),
       ('Jetta', 'Volkswagen', 'GASOLINE', 2025, 'MANUAL', 110, 'Trendline', 290000),
       ('9-3 Coupe', 'Saab', 'GASOLINE', 2024, 'MANUAL', 150, 'Vector', 270000);



CREATE TABLE car (
                     vehicle_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                     fk_car_model_id INT,
                     vin_number VARCHAR(50) UNIQUE,
                     color VARCHAR(50),
                     bought BOOLEAN DEFAULT FALSE,
                     status ENUM('READY', 'READY_FOR_TRANSPORT', 'GETTING_REPAIRED', 'RENTED') DEFAULT 'READY',
                     FOREIGN KEY (fk_car_model_id) REFERENCES car_model(car_model_id),
                     received_date TIMESTAMP
);

INSERT INTO car (fk_car_model_id, vin_number, color, bought, status, received_date)
VALUES
    (1, '1HGBH41JXMN109186', 'Red', TRUE, 'RENTED', '2025-01-01'),
    (2, 'JH4DA9470PS008042', 'Bronze', TRUE,'RENTED', '2025-01-01'),
    (5, 'WBABW33426PX70804', 'Black', TRUE,'RENTED', '2025-01-01'),
    (3, '3C3CFFBRXF1509101', 'Grey', TRUE,'RENTED', '2025-01-01'),
    (4, 'ZFAGA491XD3202032', 'Blue', TRUE,'RENTED', '2025-01-01'),
    (5, '3C3AFFAR9FT534410', 'Black', TRUE,'RENTED', '2025-01-01'),
    (6,'1G1ZD5ST9RS123456','Grey', TRUE,'RENTED', '2025-01-01'),
    (7, 'WVWZZZ3BZSE456789','White', TRUE,'RENTED', '2025-01-01'),
    (8, 'JHMFA165XPS987654', 'Orange', TRUE,'RENTED', '2025-01-01'),
    (9, '3VW2K7AJ1SM234321', 'Red', TRUE,'RENTED', '2025-01-01'),
    (10,'YS3FD59Y9R7001122','Black', TRUE,'RENTED', '2025-01-01');


CREATE TABLE customer (
                          customer_id INT PRIMARY KEY AUTO_INCREMENT,
                          first_name VARCHAR(50) NOT NULL,
                          last_name VARCHAR(50),
                          email VARCHAR(255),
                          phone_number VARCHAR(20),
                          address VARCHAR(100),
                          city VARCHAR(100),
                          postal_code INT,
                          cpr_number VARCHAR(100),
                          fk_vehicle_id INT,
                          FOREIGN KEY (fk_vehicle_id) REFERENCES car(vehicle_id)
);

INSERT INTO customer (customer_id, first_name, last_name, email, phone_number, address, city, postal_code, cpr_number, fk_vehicle_id)
    VALUES (1, 'DemoKunde', 'DemoKunde', 'demo@kunde.dk', 87654321, 'Gadevej 17','Herning', 7000, '123456-1234', 1),
           (2, 'Oliver', 'Larsen', 'oliver.larsen@example.com', 87654321, 'Elm St 3', 'Aarhus', 8000, '202901-2345', 2),
           (3, 'Sofie', 'Nielsen', 'sofie.nielsen@example.com', 11112222, 'Birch Ave 5', 'Odense', 5000, '303901-456', 3),
           (4, 'William', 'Pedersen', 'william.pedersen@example.com', 22223333, 'Oak St 7', 'Aalborg', 9000, '404901-4567', 4),
           (5, 'Freja', 'Andersen', 'freja.andersen@example.com', 33334444, 'Pine Rd 9', 'Esbjerg', 6700, '505903-5678', 5),
           (6, 'Lucas', 'Christensen', 'lucas.christensen@example.com', 44445555, 'Maple St 11', 'Randers', 8900, '606910-6789', 6),
           (7, 'Ida', 'Jensen', 'ida.jensen@example.com', 55556666, 'Fir Ln 13', 'Horsens', 8700, '707903-7890', 7),
           (8, 'Noah', 'Madsen', 'noah.madsen@example.com', 66667777, 'Cedar Blvd 15', 'Kolding', 6000, '808901-8901', 8),
           (9, 'Clara', 'Thomsen', 'clara.thomsen@example.com', 77778888, 'Spruce Dr 17', 'Vejle', 7100, '909904-9012', 9),
           (10, 'Oscar', 'Poulsen', 'oscar.poulsen@example.com', 88889999, 'Ash Ct 19', 'Herning', 7400, '101091-0123', 10);


CREATE TABLE lease_agreement (
                            lease_agreement_id INT PRIMARY KEY AUTO_INCREMENT,
                            fk_vehicle_id INT,
                            fk_customer_id INT,
                            lease_type ENUM('LIMITED', 'UNLIMITED'),
                            lease_start_date TIMESTAMP,
                            lease_end_date TIMESTAMP,
                            lease_price DOUBLE (10, 2),
                            return_location VARCHAR(255),
                            FOREIGN KEY (fk_vehicle_id) REFERENCES car(vehicle_id),
                            FOREIGN KEY (fk_customer_id) REFERENCES customer(customer_id),
                            lease_active boolean


);

INSERT INTO lease_agreement(lease_agreement_id, fk_vehicle_id, fk_customer_id, lease_type, lease_start_date, lease_end_date, lease_price, return_location, lease_active)
VALUES
    (1, 1, 1, 'UNLIMITED','2025-01-01', '2025-05-01', 10000.00, 'Guldgade',true),
    (2, 2, 2, 'UNLIMITED', '2025-04-03', '2025-11-03', 12000.00, 'Aarhus',true),
    (3, 3, 3, 'LIMITED', '2025-03-15', '2025-09-15', 8000.00, 'Odense',true),
    (4, 4, 4, 'UNLIMITED', '2025-01-10', '2025-05-10', 11000.00, 'Aalborg',true),
    (5, 5, 5, 'LIMITED', '2025-05-10', '2025-12-10', 9000.00, 'Esbjerg',true),
    (6, 6, 6, 'LIMITED', '2025-04-20', '2025-10-20', 8500.00, 'Randers',true),
    (7, 7, 7, 'UNLIMITED', '2025-03-05', '2025-09-05', 13000.00, 'Horsens',true),
    (8, 8, 8, 'LIMITED', '2025-02-01', '2025-08-01', 7800.00, 'Kolding',true),
    (9, 9, 9, 'UNLIMITED', '2025-01-23', '2025-05-23', 10000.00, 'Vejle',true),
    (10, 10, 10, 'LIMITED', '2025-05-01', '2025-11-01', 9500.00, 'Herning',true);

CREATE TABLE purchase_agreement(
                                   purchase_agreement_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                                   fk_vehicle_id INT,
                                   fk_customer_id INT,
                                   paid BOOLEAN,
                                   car_price DOUBLE(10, 2),
                                   FOREIGN KEY (fk_vehicle_id) REFERENCES car(vehicle_id),
                                   FOREIGN KEY (fk_customer_id) REFERENCES customer(customer_id)
);
INSERT INTO purchase_agreement(fk_vehicle_id, fk_customer_id, paid, car_price)
VALUES
    (1, 1, TRUE, 150000.00),
    (2, 2, FALSE, 135000.00),
    (3, 3, TRUE, 142000.00),
    (4, 4, TRUE, 160000.00),
    (5, 5, FALSE, 128000.00),
    (6, 6, TRUE, 147000.00),
    (7, 7, TRUE, 133000.00),
    (8, 8, FALSE, 139000.00),
    (9, 9, TRUE, 145000.00),
    (10, 10, FALSE, 150000.00);



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

CREATE TABLE condition_report (
                                  condition_report_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE ,
                                  fk_vehicle_id INT,
                                  handled_by VARCHAR(50),
                                  report_start_date TIMESTAMP,
                                  report_completed_date TIMESTAMP DEFAULT NULL,
                                  excess_kilometers DOUBLE DEFAULT NULL,
                                  completed BOOLEAN DEFAULT FALSE,
                                  report_description VARCHAR(300),
                                  FOREIGN KEY (fk_vehicle_id) REFERENCES car(vehicle_id)
);

INSERT INTO condition_report(fk_vehicle_id, handled_by, report_start_date, report_completed_date, excess_kilometers, completed, report_description)
VALUES
    (1, 'JÜHIN', '2025-05-08', '2025-05-09', 10, true, 'Bil i ok tilstand, ingen større skader' );

INSERT INTO condition_report(fk_vehicle_id, handled_by, report_start_date) VALUES
    (1, 'JÜHIN', '2025-05-08');

INSERT INTO purchase_agreement(fk_vehicle_id, fk_customer_id, paid, car_price)
VALUES
    (1, 1, TRUE, 150000.00),
    (2, 2, FALSE, 135000.00),
    (3, 3, TRUE, 142000.00),
    (4, 4, TRUE, 160000.00),
    (5, 5, FALSE, 128000.00),
    (6, 6, TRUE, 147000.00),
    (7, 7, TRUE, 133000.00),
    (8, 8, FALSE, 139000.00),
    (9, 9, TRUE, 145000.00),
    (10, 10, FALSE, 150000.00);


CREATE TABLE damage(
                       damage_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE ,
                        fk_condition_report_id INT,
                       damage_type VARCHAR(255),
                       damage_price DECIMAL(10,2),
                        FOREIGN KEY (fk_condition_report_id) REFERENCES condition_report(condition_report_id)
);
INSERT INTO damage(damage_id, fk_condition_report_id, damage_type, damage_price)
VALUES
    (1,1, 'Ridse på venstre dør', 500.00),
    (2,1 ,'Stenslag på forrude', 300.00),
    (3, 1,'Bule i kofanger', 750.00),
    (4,2, 'Nyt dæk', 500.00),
    (5,2 ,'Ridse på fælge', 300.00),
    (6, 2,'Ødelagt baglygte', 750.00);


