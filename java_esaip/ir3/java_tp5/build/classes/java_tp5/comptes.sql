USE `comptes`;

DROP TABLE IF EXISTS `Compte`;
DROP TABLE IF EXISTS `LigneComptable`;

create table Compte 
(idCompte integer not null AUTO_INCREMENT,
type varchar(7) not null,
numero integer not null,
solde integer not null,
taux integer not null,
constraint pk_Compte primary key(idCompte))
ENGINE=INNODB;

create table LigneComptable
(idLigneComptable integer not null AUTO_INCREMENT,
montant integer not null,
date varchar(10) not null,
motif varchar(12) not null,
modePaiement varchar(8) not null,
idCompte integer not null,
constraint pk_LigneComptable primary key(idLigneComptable), 
constraint fk1_LigneComptable foreign key(idCompte) references Compte(idCompte) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

INSERT INTO Compte (type, numero, solde, taux) VALUES ('courant', 1, 100, 0);
INSERT INTO Compte (type, numero, solde, taux) VALUES ('joint', 2, 200, 0);
INSERT INTO Compte (type, numero, solde, taux) VALUES ('epargne', 3, 100, 10);