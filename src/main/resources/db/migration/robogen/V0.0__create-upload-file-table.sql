CREATE DATABASE IF NOT EXISTS `robogen`;
USE `robogen`;

CREATE TABLE IF NOT EXISTS input_files
(
    id                BIGINT AUTO_INCREMENT PRIMARY KEY,
    -- Foreign key referencing webapp.application_user.id
    -- -1 as a default value to represent an anonymous user
    user_id           BIGINT    DEFAULT -1,
    feature_file_name VARCHAR(255) NOT NULL,
    xml_file_name     VARCHAR(255) NOT NULL,
    xsd_file_name     VARCHAR(255), -- NULL if no XSD file
    feature_file      LONGBLOB, -- Binary data for Feature files
    xml_file          LONGBLOB, -- Binary data for XML files
    xsd_file          LONGBLOB, -- Binary data for XSD files
    uploaded_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- CHECK constraint to ensure xsd_file_name exists when xsd_file is not empty
    CONSTRAINT check_xsd_filename
        CHECK ((xsd_file IS NULL AND xsd_file_name IS NULL) OR
               (xsd_file IS NOT NULL AND xsd_file_name IS NOT NULL)),
    FOREIGN KEY (user_id) REFERENCES webapp.application_user (id)
);
