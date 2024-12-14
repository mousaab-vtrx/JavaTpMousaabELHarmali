DROP TABLE IF EXISTS holidays;
DROP TABLE IF EXISTS employees
CREATE TABLE holidays (
    idemployee INTEGER PRIMARY KEY,
    startdate DATE not null,
    enddate DATE not null,
    holidaytype VARCHAR not null
);
CREATE TABLE employees (
    id INTEGER PRIMARY KEY,
    nom VARCHAR(30),
    prenom VARCHAR(30),
    email VARCHAR not null,
    salaire NUMERIC check(salaire >= 4000),
    role VARCHAR,
    poste VARCHAR,
    telephone CHAR(10),
    solde INTEGER not null
);

