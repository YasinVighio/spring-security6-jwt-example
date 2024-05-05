CREATE TABLE USER_ROLE_TL (
    USER_ROLE_ID INT PRIMARY KEY,
    USER_ROLE_NAME VARCHAR(255) NOT NULL,
    USER_ROLE_DESC TEXT
);


CREATE TABLE USER_TL (
    USER_ID INT PRIMARY KEY,
    USERNAME VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME VARCHAR(255) NOT NULL,
    IS_ACTIVE TINYINT(1) NOT NULL,
    USER_ROLE_ID INT NOT NULL,
    FOREIGN KEY (USER_ROLE_ID) REFERENCES USER_ROLE_TL(USER_ROLE_ID)
);

insert into user_role_tl values(1, 'Admin', 'Superuser');

insert into user_role_tl values(2, 'Normal User', 'normal');


insert into user_tl values(1, 'su', '$2a$10$C4bVAzjiuiAzXtusF3xiIOhSiQpb0CaUzGoDUcYXmJevdbHZ8YbUK', 'test', 'user', 1, 1); 
-- su, Hello


insert into user_tl values(2, 'normal', '$2a$10$FOCbDdlV2kcB/5WfuXbRwuxAopWXd.8W80kfHOUhPTYmL3drZkYMm', 'test2', 'user2', 1, 2);
-- normal bye2



select * from user_tl ut 
