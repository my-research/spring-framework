-- init.sql

-- Create the employees table
CREATE TABLE employees
(
    emp_id SERIAL PRIMARY KEY,
    name   VARCHAR(100) NOT NULL
);

-- Insert data into the employees table
INSERT INTO employees (name)
VALUES ('John Doe'),
       ('Jane Smith'),
       ('Michael Johnson');
