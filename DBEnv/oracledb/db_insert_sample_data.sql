INSERT INTO program (program_id,name) VALUES (1,'Computer Science');
INSERT INTO program (program_id,name) VALUES (2,'Computer Engeneering');



INSERT INTO teacher_level (level_code) VALUES ('Assistant');
INSERT INTO teacher_level (level_code) VALUES ('Associate Professor');
INSERT INTO teacher_level (level_code) VALUES ('Professor');



INSERT INTO teacher (teacher_id, name, idcode, level_code)
VALUES (1, 'Alice', '999999999', 'Professor');

INSERT INTO teacher (teacher_id, name, idcode, level_code)
VALUES (2, 'Bob', '777777777', 'Professor');

INSERT INTO teacher (teacher_id, name,  idcode, level_code)
VALUES (3, 'Charlie', '555555555', 'Assistant');

INSERT INTO teacher (teacher_id, name,  idcode, level_code)
VALUES (4, 'Guntars', '1234567890', 'Professor');




INSERT INTO student (student_id, name, idcode, program_id)
VALUES (1, 'Dave', '123456789', 1);

INSERT INTO student (student_id, name, idcode, program_id)
VALUES (2, 'Eve', '987654321', 2);

INSERT INTO student (student_id, name, idcode, program_id)
VALUES (3, 'Charlie', '555555555', 1);

INSERT INTO student (student_id, name, idcode, program_id)
VALUES (4, 'Ivan', '345453432', 2);

INSERT INTO student (student_id, name, idcode, program_id)
VALUES (5, 'Sukh', '987654321', 1);


INSERT INTO course (course_id, name, program_id, teacher_id,  required)
VALUES (1, 'Programming Basics', 2,3,0);

INSERT INTO course (course_id, name, program_id, teacher_id, required)
VALUES (2, 'Semantic Web', 1,1,1);

INSERT INTO course (course_id, name,  program_id, teacher_id, required)
VALUES (3, 'Computer Networks', 2,2,1);

INSERT INTO course (course_id, name, program_id, teacher_id, required)
VALUES (4, 'Quantum Computations', 1,2,0);

INSERT INTO course (course_id, name, program_id, teacher_id, required)
VALUES (5, 'DataBase 2', 1, 4, 1);



INSERT INTO registration (registration_id,student_id,course_id)
VALUES (1, 1, 2 );

INSERT INTO registration (registration_id,student_id,course_id)
VALUES (2, 1, 4);

INSERT INTO registration (registration_id,student_id,course_id)
VALUES (3, 2, 1);

INSERT INTO registration (registration_id,student_id,course_id)
VALUES (4, 2, 3);

INSERT INTO registration (registration_id,student_id,course_id)
VALUES (5, 3, 2);

INSERT INTO registration (registration_id,student_id,course_id)
VALUES (6, 5, 5);


commit;