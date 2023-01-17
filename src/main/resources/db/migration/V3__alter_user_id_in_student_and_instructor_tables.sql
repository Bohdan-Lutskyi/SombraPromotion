DELIMITER $$
DROP PROCEDURE IF EXISTS alterColumnInTable $$
CREATE PROCEDURE alterColumnInTable()
BEGIN
    IF EXISTS(SELECT *
              FROM information_schema.COLUMNS
              WHERE TABLE_SCHEMA = DATABASE()
                AND COLUMN_NAME = 'student'
                AND TABLE_NAME = 'user_id')
    THEN
        ALTER TABLE promotion.student
            MODIFY user_id BIGINT NOT NULL UNIQUE;
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
                AND COLUMN_NAME = 'instructor'
                AND TABLE_NAME = 'user_id')
    THEN
        ALTER TABLE promotion.instructor
            MODIFY user_id BIGINT NOT NULL UNIQUE;
    END IF;
END $$
CALL alterColumnInTable() $$
DELIMITER ;