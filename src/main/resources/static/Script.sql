DROP DATABASE IF EXISTS Fleetmanager_db;
CREATE DATABASE Fleetmanager_db;
USE Fleetmanager_db;


CREATE TABLE users (
                       user_id INT PRIMARY KEY AUTO_INCREMENT,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       short_name VARCHAR(50),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role ENUM('mekaniker', 'admin', 'regnskab') NOT NULL
);

INSERT INTO users (user_id, first_name,last_name, short_name, email, password, role) VALUES
                                    (1, 'demo','demo', 'demo', 'demo','demo','admin');

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

INSERT INTO car_model (car_model_id, model_year, brand, model, car_emission, car_equipment, steel_price, registration_fee) VALUES
                                    (1, 2020, 'Mercedes', 'SLS', 258, 'Sport', 1000, 100);


CREATE TABLE rental_car (
                            vehicle_id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
                            car_model_id INT,
                            vin_number VARCHAR(50) UNIQUE,
                            color VARCHAR(50),
                            return_address VARCHAR(255),
                            price DECIMAL(10,2),
                            mileage INT,
                            status ENUM('klar', 'skadet', 'til_klargøring', 'udlejet') DEFAULT 'klar',
                            FOREIGN KEY (car_model_id) REFERENCES car_model(car_model_id)

);

INSERT INTO rental_car(vehicle_id, car_model_id, vin_number, color, return_address, price, mileage, status) VALUES
                                    (1, 1, 'EW33357', 'black', 'Guldbergsgade 29', 10000, 5000, 'klar');

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
                        contract_price DECIMAL(10,2),
                        advance_buyer BOOLEAN,
                        FOREIGN KEY (vehicle_id) REFERENCES rental_car(vehicle_id)
);

INSERT INTO bookings (booking_id, vehicle_id, customer_name, customer_email, customer_phone, lease_type, lease_duration, lease_start_date, lease_end_date, contract_price, advance_buyer) VALUES
                                    (1, 1, 'Frederik Morsing', 'frederikmorsing@gmail.com', 50496373, 'limited', 90, '2025-05-01', '2025-08-01', 30000, true);

CREATE TABLE buyers (
                        buyer_id INT PRIMARY KEY AUTO_INCREMENT,
                        full_name VARCHAR(255) NOT NULL,
                        email VARCHAR(255),
                        phone_number VARCHAR(20),
                        is_pre_bought BOOLEAN,
                        vehicle_id INT,
                        FOREIGN KEY (vehicle_id) REFERENCES rental_car(vehicle_id)
);

INSERT INTO buyers (buyer_id, full_name, email, phone_number, is_pre_bought, vehicle_id) VALUES
                                    (1, 'Noah Thomsen', 'noah@email.dk', 33424101, true, 1);

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

INSERT INTO damage_reports (report_id, vehicle_id, report_date, damage_type, damage_price, handled_by) VALUES
                                    (1, 1, '2025-06-01', 'Dent', 2500, 1);

CREATE TABLE vehicle_returns (
                        return_id INT PRIMARY KEY AUTO_INCREMENT,
                        vehicle_id INT,
                        return_date DATE,
                        returned_to ENUM('FDM', 'Bilabonnement'),
                        report_id INT,
                        FOREIGN KEY (vehicle_id) REFERENCES rental_car(vehicle_id),
                        FOREIGN KEY (report_id) REFERENCES damage_reports(report_id)
);

INSERT INTO vehicle_returns (return_id, vehicle_id, return_date, returned_to, report_id) VALUES
                                    (1, 1, '2025-08-01', 'Bilabonnement', 1);

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
