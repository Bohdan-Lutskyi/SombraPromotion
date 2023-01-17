DELIMITER $$
DROP PROCEDURE IF EXISTS alterColumnInTable $$
CREATE PROCEDURE alterColumnInTable()
BEGIN
    IF NOT EXISTS(SELECT *
                  FROM information_schema.TABLES
                  WHERE TABLE_SCHEMA = DATABASE()
                    AND TABLE_NAME = 'student_attachment')
    THEN
        create table student_attachment
        (
            id                 bigint       not null auto_increment primary key,
            original_file_name varchar(500) NOT NULL,
            stored_file_name   varchar(500) NOT NULL,
            lesson_id          bigint       NOT NULL,
            created_by         varchar(255),
            created_date       datetime(6),
            modified_by        varchar(255),
            modified_date      datetime(6),
            CONSTRAINT FOREIGN KEY (lesson_id) REFERENCES lesson (id)
        );
    END IF;
END $$
CALL alterColumnInTable() $$
DELIMITER ;