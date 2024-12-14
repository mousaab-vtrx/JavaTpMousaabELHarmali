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
    nom VARCHAR(100),
    prenom VARCHAR(100),
    email VARCHAR(100),
    salaire NUMERIC check(salaire >= 4000),
    role VARCHAR(50) ,
    poste VARCHAR(50),
    telephone VARCHAR(10),
    solde INTEGER n
);

