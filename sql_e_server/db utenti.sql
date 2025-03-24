
drop schema if exists eventfinder;

create schema if not exists eventfinder;

use eventfinder;

create table Utenti(
id INT AUTO_INCREMENT  PRIMARY KEY,
nome VARCHAR (200) NOT NULL,
cognome VARCHAR (200) NOT NULL,
email VARCHAR (200) UNIQUE NOT NULL,
data_di_nascita date NOT NULL,
password_hash VARCHAR(255) NOT NULL);
select * from utenti;

create table eventi(
id INT AUTO_INCREMENT  PRIMARY KEY,
titolo VARCHAR (200) NOT NULL,
data_evento VARCHAR (200) NOT NULL,
luogo VARCHAR (200) UNIQUE NOT NULL,
categoria varchar(200) NOT NULL,
info_evento VARCHAR(255) NOT NULL,
info_artista varchar(200) not null
);

select * from utenti;