DROP TABLE IF EXISTS Trade;
CREATE TABLE Trade (
Trade_Id VARCHAR(50) PRIMARY KEY NOT NULL,
Counter_Party_ID VARCHAR(50) NOT NULL,
Book_ID VARCHAR(50) NOT NULL,
Maturity_Date DATE,
Created_Date DATE,
Version INT(8) NOT NULL,
Expired VARCHAR(50)
);