create table Utenti(
id INT AUTO_INCREMENT  PRIMARY KEY,
nome VARCHAR (200) NOT NULL,
cognome VARCHAR (200) NOT NULL,
email VARCHAR (200) UNIQUE NOT NULL,
data_di_nascita date NOT NULL,
password_hash VARCHAR(255) NOT NULL);
select * from utenti;