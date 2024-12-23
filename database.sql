-- DATABASE NAME :JavaTp
DROP TRIGGER IF EXISTS trigSoldeLogicUpdate ON holidays;
DROP TRIGGER IF EXISTS trigSoldeLogicDelete ON holidays;

DROP FUNCTION IF EXISTS retrieveOldValuesUpdate;
DROP FUNCTION IF EXISTS retrieveOldValuesDelete;

DROP TABLE IF EXISTS holidays CASCADE;
DROP TABLE IF EXISTS employees CASCADE;
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE employees (
    idEmployee serial PRIMARY KEY,
    nom VARCHAR(30),
    prenom VARCHAR(30),
    email VARCHAR(50) NOT NULL,
    salaire NUMERIC CHECK(salaire >= 4000),
    role VARCHAR,
    poste VARCHAR,
    telephone VARCHAR(10),
    solde INTEGER DEFAULT 25
);

CREATE TABLE holidays (
    id serial PRIMARY KEY,
    idEmployee INTEGER,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL,
    holidaytype VARCHAR NOT NULL,
    CONSTRAINT idEmployee_fkey FOREIGN KEY (idEmployee) REFERENCES employees (idEmployee)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE users (
    id serial PRIMARY KEY,
    username VARCHAR NOT NULL,
    password CHAR(8) NOT NULL
);

CREATE OR REPLACE FUNCTION retrieveOldValuesUpdate() 
RETURNS TRIGGER LANGUAGE plpgsql AS $$
DECLARE
    oldDays INTEGER;
    newDays INTEGER;
BEGIN 
    oldDays := (OLD.enddate - OLD.startdate + 1);
    newDays := (NEW.enddate - NEW.startdate + 1);

    UPDATE employees
    SET solde = solde + oldDays - newDays
    WHERE idEmployee = NEW.idEmployee;

    RETURN NEW;
END;
$$;

CREATE OR REPLACE FUNCTION retrieveOldValuesDelete() 
RETURNS TRIGGER LANGUAGE plpgsql AS $$
DECLARE
    days INTEGER;
BEGIN 
    days := (OLD.enddate - OLD.startdate + 1);

    UPDATE employees
    SET solde = solde + days
    WHERE idEmployee = OLD.idEmployee;

    RETURN OLD;
END;
$$;

CREATE TRIGGER trigSoldeLogicUpdate 
BEFORE UPDATE ON holidays 
FOR EACH ROW 
EXECUTE FUNCTION retrieveOldValuesUpdate();

CREATE TRIGGER trigSoldeLogicDelete 
BEFORE DELETE ON holidays 
FOR EACH ROW 
EXECUTE FUNCTION retrieveOldValuesDelete();

INSERT INTO users(username, password) 
VALUES 
    ('mousaab', '12344321'),
    ('aya', '56788765'),
    ('sara', 'sarasara'),
    ('amine', 'aminamin');

SELECT * FROM employees;
