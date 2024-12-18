--DATABASE NAME :JavaTp
DROP TRIGGER IF EXISTS trigSoldeLogic on holidays;
DROP FUNCTION IF EXISTS retrieveOldValues;
DROP TABLE IF EXISTS holidays;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS users;


CREATE TABLE employees (
    idEmployee serial PRIMARY KEY,
    nom VARCHAR(30),
    prenom VARCHAR(30),
    email VARCHAR(50) NOT NULL,
    salaire NUMERIC CHECK(salaire >= 4000),
    role VARCHAR,
    poste VARCHAR,
    telephone CHAR(10),
    solde INTEGER default 25
);

CREATE TABLE holidays (
    id serial primary key,
    idEmployee INTEGER,
    startdate DATE NOT NULL,
    enddate DATE NOT NULL,
    holidaytype VARCHAR NOT NULL,
	CONSTRAINT idEmployee_fkey FOREIGN KEY (idEmployee) REFERENCES employees (idEmployee)
	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE users(
	id Serial PRIMARY KEY,
	username VARCHAR not null,
	password char(8) not null 
);
Create or Replace FUNCTION retrieveOldValues() 
returns Trigger language plpgsql AS $$
Begin 
    update employees set solde = OLD.solde where idEmployee = NEW.idEmployee;
    return NEW;
END;
$$;

Create Trigger trigSoldeLogic  before update ON holidays for each row execute Function retrieveOldValues();
select * from employees;
