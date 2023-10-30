-- Create the 'developer' user
CREATE USER 'developer'@'%' IDENTIFIED WITH 'caching_sha2_password' BY 'dev1234*';

-- Grant access to the 'webapp' and 'hotel' schemas
GRANT ALL PRIVILEGES ON webapp.* TO 'developer'@'%';
GRANT ALL PRIVILEGES ON hotel.* TO 'developer'@'%';
GRANT ALL PRIVILEGES ON robogen.* TO 'developer'@'%';

-- Reload the privileges
FLUSH PRIVILEGES;

-- create databases
CREATE DATABASE IF NOT EXISTS `webapp`;
CREATE DATABASE IF NOT EXISTS `hotel`;
CREATE DATABASE IF NOT EXISTS `robogen`;