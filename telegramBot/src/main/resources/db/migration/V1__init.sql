CREATE TABLE customer (
    ID bigserial PRIMARY KEY,
    lastName TEXT NOT NULL,
    firstName TEXT NOT NULL,
    telephone TEXT NOT NULL,
    telegram_id TEXT NOT NULL
);


CREATE TABLE master (
    ID bigserial PRIMARY KEY,
    lastName TEXT NOT NULL,
    firstName TEXT NOT NULL,
    telephone TEXT NOT NULL
);


CREATE TABLE profession (
    ID bigserial PRIMARY KEY,
    name TEXT NOT NULL
);


CREATE TABLE master_profession (
    master_ID bigserial,
    profession_ID bigserial,
    FOREIGN KEY (master_ID) REFERENCES master(ID),
    FOREIGN KEY (profession_ID) REFERENCES profession(ID),
    PRIMARY KEY (master_ID, profession_ID)
);


CREATE TABLE orders (
    ID bigserial PRIMARY KEY,
    customer_ID bigserial,
    master_ID bigserial,
    date_time text,
    FOREIGN KEY (customer_ID) REFERENCES customer(ID),
    FOREIGN KEY (master_ID) REFERENCES master(ID)
);

CREATE TABLE data_time (
    ID bigserial PRIMARY KEY,
    date text,
    master_ID bigserial,
    FOREIGN KEY (master_ID) REFERENCES master(ID)
);

INSERT INTO master (lastname, firstname, telephone) VALUES
   ('USER_1', 'ONE', '+7999-892-12-12'),
   ('USER_2', 'TWO', '+7999-892-12-13'),
   ('USER_3', 'THREE', '+7999-892-12-14');

INSERT INTO data_time (date, master_ID) values
    ('10/03/2025 10:00', 1),
    ('10/03/2025 11:00', 1),
    ('10/03/2025 12:00', 1),
    ('10/03/2025 10:00', 2),
    ('10/03/2025 11:00', 2),
    ('10/03/2025 12:00', 2),
    ('10/03/2025 10:00', 3),
    ('10/03/2025 11:00', 3),
    ('10/03/2025 12:00', 3);

INSERT INTO profession (name) VALUES
    ('Маникюр'),
    ('Парикмахер');

INSERT INTO master_profession (master_ID, profession_ID) VALUES
    (1, 1),
    (2, 2),
    (3, 2);

