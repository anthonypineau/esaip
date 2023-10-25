DROP TABLE IF EXISTS `PlayersItems`;
DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `Items`;
DROP TABLE IF EXISTS `ItemClasses`;
DROP TABLE IF EXISTS `Mounts`;
DROP TABLE IF EXISTS `Players`;
DROP TABLE IF EXISTS `Classes`;
DROP TABLE IF EXISTS `Races`;
DROP TABLE IF EXISTS `Professions`;

create table Users
(id int not null auto_increment,
username varchar(45) not null,
password varchar(150) not null,
constraint pk_Users primary key(id))
ENGINE=INNODB;

create table ItemClasses
(id int not null auto_increment,
name varchar(45) not null,
constraint pk_ItemClasses primary key(id))
ENGINE=INNODB;

create table Items
(id int not null auto_increment, 
name varchar(45) not null,
quality varchar(45) not null,
level int not null,
requiredLevel int not null,
purchasePrice int not null,
sellPrice int not null,
isEquippable bit(1) not null,
isStackable bit(1) not null,
media varchar(250),
itemClass int not null,
constraint pk_Items primary key(id),
constraint fk1_Items foreign key(itemClass) references ItemClasses(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

create table Classes
(id int not null auto_increment,
name varchar(45) not null,
powerType varchar(45) not null,
media varchar(250) not null,
constraint pk_Classes primary key(id))
ENGINE=INNODB;

create table Races
(id int not null auto_increment,
name varchar(45) not null,
faction varchar(45) not null,
constraint pk_Races primary key(id))
ENGINE=INNODB;

create table Professions
(id int not null auto_increment,
name varchar(45) not null,
description text not null,
type varchar(45) not null,
media varchar(250) not null,
constraint pk_Professions primary key(id))
ENGINE=INNODB;

create table Players
(id int not null auto_increment,
username varchar(45) not null,
isConnected bit(1) not null,
level int not null,
icon varchar(250) not null,
class int not null,
race int not null,
profession int not null,
constraint pk_Players primary key(id),
constraint fk1_Players foreign key(class) references Classes(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk2_Players foreign key(race) references Races(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk3_Players foreign key(profession) references Professions(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

create table Mounts
(id int not null auto_increment,
name varchar(45) not null,
description text not null,
source varchar(45) not null,
faction varchar(45) not null,
media varchar(250) not null,
player int not null,
constraint pk_Mounts primary key(id),
constraint fk1_Mounts foreign key(player) references Players(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

create table PlayersItems
(item int not null,
player int not null,
numberInInventory int not null,
constraint pk_PlayersItems primary key(item,player),
constraint fk1_PlayersItems foreign key(item) references Items(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk2_PlayersItems foreign key(player) references Players(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;