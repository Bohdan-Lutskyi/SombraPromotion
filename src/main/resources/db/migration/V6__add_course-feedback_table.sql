DELIMITER $$
DROP PROCEDURE IF EXISTS alterColumnInTable $$
CREATE PROCEDURE alterColumnInTable()
BEGIN
    IF NOT EXISTS(SELECT *
                  FROM information_schema.TABLES
                  WHERE TABLE_SCHEMA = DATABASE()
                    AND TABLE_NAME = 'course_feedback')
    THEN
        create table course_feedback
        (
            id            bigint not null auto_increment primary key,
            feedback      text   NOT NULL,
            course_id     bigint NOT NULL,
            student_id    bigint NOT NULL,
            created_by    varchar(255),
            created_date  datetime(6),
            modified_by   varchar(255),
            modified_date datetime(6),
            CONSTRAINT FOREIGN KEY (course_id) REFERENCES course (id),
            CONSTRAINT FOREIGN KEY (student_id) REFERENCES student (id)
        );
    END IF;
END $$
CALL alterColumnInTable() $$
DELIMITER ;