DELIMITER $$
DROP PROCEDURE IF EXISTS alterColumnInTable $$
CREATE PROCEDURE alterColumnInTable()
BEGIN
    IF EXISTS(SELECT *
              FROM information_schema.COLUMNS
              WHERE TABLE_SCHEMA = DATABASE()
                AND COLUMN_NAME = 'user_role'
                AND TABLE_NAME = 'user')
    THEN
        ALTER TABLE promotion.user
            MODIFY user_role VARCHAR(100) NOT NULL;
        UPDATE promotion.user SET user_role = '["STUDENT"]' where user_role = 'STUDENT';
        UPDATE promotion.user SET user_role = '["ADMIN"]' where user_role = 'ADMIN';
        UPDATE promotion.user SET user_role = '["INSTRUCTOR"]' where user_role = 'INSTRUCTOR';
    END IF;
END $$
CALL alterColumnInTable() $$
DELIMITER ;

DELIMITER $$
DROP PROCEDURE IF EXISTS alterColumnInTable $$
CREATE PROCEDURE alterColumnInTable()
BEGIN
    IF EXISTS(SELECT *
              FROM information_schema.COLUMNS
              WHERE TABLE_SCHEMA = DATABASE()
                AND COLUMN_NAME = 'user_role'
                AND TABLE_NAME = 'user')
    THEN
        ALTER TABLE promotion.user
            RENAME COLUMN user_role TO user_roles;
    END IF;
END $$
CALL alterColumnInTable() $$
DELIMITER ;