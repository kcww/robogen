USE `webapp`;

-- Insert a placeholder for anonymous user
INSERT INTO `application_user` (`id`, `version`, `hashed_password`, `name`, `profile_picture`, `username`)
VALUES (
           -1, -- Use -1 to represent anonymous user
           0, -- Set the version to 0
           NULL, -- No hashed password for anonymous user
           'Anonymous User', -- A recognizable name for anonymous user
           NULL, -- No profile picture for anonymous user
           'anonymous' -- A unique username for anonymous user
       );

COMMIT;