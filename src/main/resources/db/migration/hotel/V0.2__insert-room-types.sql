USE hotel;

INSERT IGNORE INTO room_types (`name`)
VALUES ('Single'),
       ('Double'),
       ('Twin'),
       ('Suite'),
       ('Deluxe');

COMMIT;