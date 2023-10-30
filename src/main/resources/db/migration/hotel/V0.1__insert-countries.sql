USE hotel;

INSERT IGNORE INTO countries (iso_code, `name`)
VALUES ('CN', 'China'),
       ('MY', 'Malaysia'),
       ('IN', 'India'),
       ('RU', 'Russia'),
       ('JP', 'Japan'),
       ('KR', 'South Korea'),
       ('US', 'United States'),
       ('GB', 'United Kingdom'),
       ('SG', 'Singapore'),
       ('AU', 'Australia'),
       ('VN', 'Vietnam'),
       ('DE', 'Germany'),
       ('FR', 'France'),
       ('LA', 'Laos'),
       ('MM', 'Myanmar'),
       ('HK', 'Hong Kong'),
       ('TW', 'Taiwan'),
       ('CA', 'Canada'),
       ('SE', 'Sweden'),
       ('FI', 'Finland'),
       ('TH', 'Thailand');

COMMIT;