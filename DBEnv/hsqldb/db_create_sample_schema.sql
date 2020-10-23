CREATE TABLE COURSE 
(
  COURSE_ID INTEGER NOT NULL 
, TEACHER_ID INTEGER 
, PROGRAM_ID INTEGER 
, NAME VARCHAR(40) 
, REQUIRED INTEGER 
, CONSTRAINT COURSE_PK PRIMARY KEY 
  (
    COURSE_ID 
  )
);

CREATE TABLE REGISTRATION 
(
  REGISTRATION_ID INTEGER NOT NULL 
, COURSE_ID INTEGER  NULL 
, STUDENT_ID INTEGER NULL 
, CONSTRAINT REGISTRATION_PK PRIMARY KEY 
  (
    REGISTRATION_ID 
  )
);

CREATE TABLE TEACHER 
(
  TEACHER_ID INTEGER NOT NULL 
, LEVEL_CODE VARCHAR(30) 
, IDCODE VARCHAR(30) 
, NAME VARCHAR(40) 
, CONSTRAINT TEACHER_PK PRIMARY KEY 
  (
    TEACHER_ID 
  )
);

CREATE TABLE STUDENT 
(
  STUDENT_ID INTEGER NOT NULL 
, PROGRAM_ID INTEGER 
, IDCODE VARCHAR(30) 
, NAME VARCHAR(80) 
, CONSTRAINT STUDENT_PK PRIMARY KEY 
  (
    STUDENT_ID 
  )
);

CREATE TABLE PROGRAM 
(
  PROGRAM_ID INTEGER NOT NULL 
, NAME VARCHAR(80) 
, CONSTRAINT PROGRAM_PK PRIMARY KEY 
  (
    PROGRAM_ID 
  )
);

CREATE TABLE TEACHER_LEVEL 
(
  LEVEL_CODE VARCHAR(30) NOT NULL 
, CONSTRAINT TEACHER_LEVEL_PK PRIMARY KEY 
  (
    LEVEL_CODE 
  )
);

ALTER TABLE COURSE
ADD CONSTRAINT COURSE_PROGRAM_FK FOREIGN KEY
(
  PROGRAM_ID 
)
REFERENCES PROGRAM
(
  PROGRAM_ID 
)
ON DELETE SET NULL ;

ALTER TABLE COURSE
ADD CONSTRAINT COURSE_TEACHER_FK FOREIGN KEY
(
  TEACHER_ID 
)
REFERENCES TEACHER
(
  TEACHER_ID 
)
ON DELETE SET NULL ;

ALTER TABLE REGISTRATION
ADD CONSTRAINT REGISTRATION_COURSE_FK FOREIGN KEY
(
  COURSE_ID 
)
REFERENCES COURSE
(
  COURSE_ID 
)
ON DELETE SET NULL ;

ALTER TABLE REGISTRATION
ADD CONSTRAINT REGISTRATION_STUDENT_FK FOREIGN KEY
(
  STUDENT_ID 
)
REFERENCES STUDENT
(
  STUDENT_ID 
)
ON DELETE SET NULL ;

ALTER TABLE TEACHER
ADD CONSTRAINT TEACHER_TEACHER_LEVEL_FK FOREIGN KEY
(
  LEVEL_CODE 
)
REFERENCES TEACHER_LEVEL
(
  LEVEL_CODE 
)
ON DELETE SET NULL ;

ALTER TABLE STUDENT
ADD CONSTRAINT STUDENT_PROGRAM_FK FOREIGN KEY
(
  PROGRAM_ID 
)
REFERENCES PROGRAM
(
  PROGRAM_ID 
)
ON DELETE SET NULL ;
commit;