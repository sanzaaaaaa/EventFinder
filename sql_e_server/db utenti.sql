
drop schema if exists eventfinder;

create schema if not exists eventfinder;

use eventfinder;
 

create table utenti(
id INT AUTO_INCREMENT  PRIMARY KEY,
nome VARCHAR (200) NOT NULL,
cognome VARCHAR (200) NOT NULL,
email VARCHAR (200) UNIQUE NOT NULL,
data_di_nascita date NOT NULL,
password VARCHAR(255) NOT NULL);

create table categoria(
id INT AUTO_INCREMENT  PRIMARY KEY,
tipologia VARCHAR (200) NOT NULL
);

create table eventi(
id INT AUTO_INCREMENT  PRIMARY KEY,
titolo VARCHAR (200) NOT NULL,
data_evento VARCHAR (200) NOT NULL,
luogo VARCHAR (200) UNIQUE NOT NULL,
info_evento VARCHAR(255) NOT NULL,
info_artista varchar(200) not null,
categoria_id int,
foreign key (categoria_id) references categoria(id)
);

alter table eventi
add column urlimage varchar(500);

alter table eventi
add foreign key (preferiti_id) references preferiti(id);



create table preferiti(
	id INT auto_increment primary key,
    utente_id int,
    evento_id int,
    foreign key (utente_id) references utenti(id),
    foreign key (evento_id) references eventi(id)
);

select * from artisti;


create table partecipazione(
	id INT auto_increment primary key,
    utente_id int,
    evento_id int,
    foreign key (utente_id) references utenti(id),
    foreign key (evento_id) references eventi(id)
);

create table artisti(
id INT AUTO_INCREMENT  PRIMARY KEY,
nome VARCHAR (200) NOT NULL
);

create table esibizione(
id INT AUTO_INCREMENT  PRIMARY KEY,
artista_id int,
evento_id int,
foreign key (artista_id) references artisti(id),
foreign key (evento_id) references eventi(id)
);

alter table categoria
add column genere varchar(200);

alter table eventi
add column prezzo int;

select * from utenti;
