DELIMITER $$
DROP PROCEDURE IF EXISTS addColumnToTable $$
CREATE PROCEDURE addColumnToTable()
BEGIN
    IF
        NOT EXISTS((SELECT *
                    FROM information_schema.COLUMNS
                    WHERE TABLE_SCHEMA = DATABASE()
                      AND COLUMN_NAME = 'password_hash'
                      AND TABLE_NAME = 'user'))
    THEN
        ALTER TABLE promotion.user
            ADD password_hash VARCHAR(60) NOT NULL;
    END IF;
END $$
CALL addColumnToTable() $$
DELIMITER ;