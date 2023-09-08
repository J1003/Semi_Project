CREATE TABLE Users (
    user_id VARCHAR2(20) Primary Key,,
    email VARCHAR2(50) NOT NULL,
    password VARCHAR2(20) NOT NULL,
    phoneNumber VARCHAR2(11) NOT NULL,
    birthDay VARCHAR2(8) NOT NULL,
    gender VARCHAR2(1) NOT NULL
);


CREATE TABLE CONCERT_INFO (
    concert_id NUMBER(2) Primary Key,
    title VARCHAR2(100) NOT NULL,
    genre VARCHAR2(50) NOT NULL,
    running_time NUMBER NOT NULL,
    concert_date VARCHAR2(100) NOT NULL,
    location VARCHAR2(500)
);

--김하은 생성함----------------------------------------
CREATE TABLE HALL_INFO (
	hall_id	number(2)	PRIMARY KEY,
	city	varchar2(20)	NOT NULL,
	name	varchar2(40)	NOT NULL,
	hallName	varchar2(40)	NULL
);

ALTER TABLE HALL_INFO
ADD No_Seats number(2);

--Drop Table hall_Info;

commit;
---------------------------------------------------

--김하은 생성함----------------------------------------
CREATE TABLE SEAT_INFO (
	seat_id number(2)	PRIMARY KEY,
	hall_id number(2) REFERENCES HALL_INFO (HALL_ID)
);

commit;
---------------------------------------------------

--김하은 생성함-------------------------------
CREATE TABLE RESERVATION (
	book_id	number(10) PRIMARY KEY,
	user_id	varchar2(20) REFERENCES USERS (USER_ID),
    concert_id	number(2) REFERENCES CONCERT_INFO (CONCERT_ID),
    hall_id number(2) REFERENCES HALL (HALL_ID),
	count	number(10)	NOT NULL,
	seat	number(3)	NOT NULL,
	totalPrice	number(30)	NOT NULL,
    paymentMethod	varchar2(255)	NOT NULL,
	createDate varchar2(200)	NOT NULL,
	status	varchar2(255)	NOT NULL
);  

ALTER TABLE RESERVATION
DROP CONSTRAINT fk_child_parent;

ALTER TABLE RESERVATION
ADD CONSTRAINT fk_RESERVATION_concert_info
FOREIGN KEY (concert_id) REFERENCES concert_info(concert_id)
ON DELETE CASCADE;

--Drop Table Reservation;

commit;
---------------------------------------------------





