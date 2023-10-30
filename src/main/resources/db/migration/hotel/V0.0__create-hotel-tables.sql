CREATE DATABASE IF NOT EXISTS hotel;
USE hotel;

-- country Table
CREATE TABLE IF NOT EXISTS countries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    iso_code CHAR(2) UNIQUE,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- personal_detail Table
CREATE TABLE IF NOT EXISTS personal_details (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    country_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (country_id) REFERENCES countries(id)
);

-- room_type Table
CREATE TABLE IF NOT EXISTS room_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- booking_info Table
CREATE TABLE IF NOT EXISTS booking_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    checkin_date DATE NOT NULL,
    checkout_date DATE NOT NULL,
    adults INT NOT NULL CHECK (adults BETWEEN 1 AND 100),
    children INT CHECK (children BETWEEN 0 AND 100),
    smoking_preference BOOLEAN NOT NULL,
    room_type_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES personal_details(id),
    FOREIGN KEY (room_type_id) REFERENCES room_types(id)
);

-- special_request Table
CREATE TABLE IF NOT EXISTS special_requests (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    late_checkout BOOLEAN,
    early_checkin BOOLEAN,
    room_upgrade BOOLEAN,
    `others` VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- booking_confirmation Table
CREATE TABLE IF NOT EXISTS booking_confirmations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    booking_info_id BIGINT NOT NULL,
    special_request_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES personal_details(id),
    FOREIGN KEY (booking_info_id) REFERENCES booking_info(id),
    FOREIGN KEY (special_request_id) REFERENCES special_requests(id)
);

-- Indexes
CREATE INDEX idx_countries_iso_code ON countries(iso_code);
CREATE INDEX idx_countries_name ON countries(name);
CREATE INDEX idx_personal_details_email ON personal_details(email);
