DROP TABLE IF EXISTS `PlayersItems`;
DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `Items`;
DROP TABLE IF EXISTS `ItemClasses`;
DROP TABLE IF EXISTS `Mounts`;
DROP TABLE IF EXISTS `Players`;
DROP TABLE IF EXISTS `Classes`;
DROP TABLE IF EXISTS `Races`;
DROP TABLE IF EXISTS `Professions`;
DROP TABLE IF EXISTS `Medias`;
DROP TABLE IF EXISTS `Sources`;
DROP TABLE IF EXISTS `Factions`;
DROP TABLE IF EXISTS `PowerTypes`;
DROP TABLE IF EXISTS `Types`;
DROP TABLE IF EXISTS `Qualities`;

create table Medias
(id int not null auto_increment,
link varchar(250) not null,
constraint pk_Medias primary key(id))
ENGINE=INNODB;

create table Sources
(id int not null auto_increment,
name varchar(20) not null,
constraint pk_Sources primary key(id))
ENGINE=INNODB;

create table Factions
(id int not null auto_increment,
name varchar(10) not null,
constraint pk_Factions primary key(id))
ENGINE=INNODB;

create table PowerTypes
(id int not null auto_increment,
name varchar(20) not null,
constraint pk_PowerTypes primary key(id))
ENGINE=INNODB;

create table Types
(id int not null auto_increment,
name varchar(20) not null,
constraint pk_Types primary key(id))
ENGINE=INNODB;

create table Qualities
(id int not null auto_increment,
name varchar(20) not null,
constraint pk_Qualities primary key(id))
ENGINE=INNODB;

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
quality int not null,
level int not null,
requiredLevel int not null,
purchasePrice int not null,
sellPrice int not null,
isEquippable int not null,
isStackable int not null,
media int not null,
itemClass int not null,
constraint pk_Items primary key(id),
constraint fk1_Items foreign key(itemClass) references ItemClasses(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk2_Items foreign key(media) references Medias(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk3_Items foreign key(quality) references Qualities(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

create table Classes
(id int not null auto_increment,
name varchar(45) not null,
powerType int not null,
media int not null,
constraint pk_Classes primary key(id),
constraint fk1_Classes foreign key(media) references Medias(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk2_Classes foreign key(powerType) references PowerTypes(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

create table Races
(id int not null auto_increment,
name varchar(45) not null,
faction int not null,
constraint pk_Races primary key(id),
constraint fk1_Races foreign key(faction) references Factions(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

create table Professions
(id int not null auto_increment,
name varchar(45) not null,
description text not null,
type int not null,
media int not null,
constraint pk_Professions primary key(id),
constraint fk1_Professions foreign key(media) references Medias(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk2_Professions foreign key(type) references Types(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

create table Players
(id int not null auto_increment,
name varchar(45) not null,
isConnected int not null,
level int not null,
media int not null,
class int not null,
race int not null,
profession int not null,
constraint pk_Players primary key(id),
constraint fk1_Players foreign key(class) references Classes(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk2_Players foreign key(race) references Races(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk3_Players foreign key(profession) references Professions(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk4_Players foreign key(media) references Medias(id) 
ON DELETE CASCADE ON UPDATE CASCADE)
ENGINE=INNODB;

create table Mounts
(id int not null auto_increment,
name varchar(45) not null,
description text not null,
source int not null,
faction int not null,
media int not null,
player int not null,
constraint pk_Mounts primary key(id),
constraint fk1_Mounts foreign key(player) references Players(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk2_Mounts foreign key(source) references Sources(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk3_Mounts foreign key(faction) references Factions(id) 
ON DELETE CASCADE ON UPDATE CASCADE,
constraint fk4_Mounts foreign key(media) references Medias(id) 
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

INSERT INTO users(username, password) SELECT username, password FROM wowapi.users;

INSERT INTO Medias(link) SELECT DISTINCT media FROM wowapi.Items;
INSERT INTO Medias(link) SELECT DISTINCT media FROM wowapi.Classes;
INSERT INTO Medias(link) SELECT DISTINCT icon FROM wowapi.Players;
INSERT INTO Medias(link) SELECT DISTINCT media FROM wowapi.Mounts;
INSERT INTO Medias(link) SELECT DISTINCT media FROM wowapi.Professions;
INSERT INTO Sources(name) SELECT DISTINCT source FROM wowapi.Mounts;
INSERT INTO Factions(name) SELECT DISTINCT faction FROM wowapi.Mounts;
INSERT INTO PowerTypes(name) SELECT DISTINCT powerType FROM wowapi.Classes;
INSERT INTO ItemClasses(name) SELECT name FROM wowapi.ItemClasses;
INSERT INTO Types(name) SELECT DISTINCT type FROM wowapi.Professions;

INSERT INTO Qualities(name) SELECT DISTINCT quality FROM wowapi.Items;

INSERT INTO Items(name, quality, level, requiredLevel, purchasePrice, 
sellPrice, isEquippable, isStackable, media, itemClass)
SELECT  I.name, Q.id, I.level, I.requiredLevel, I.purchasePrice, 
I.sellPrice, I.isEquippable,I.isStackable, M.id, I.itemClass
FROM wowapi.Items I INNER JOIN Medias M ON I.media=M.link INNER JOIN Qualities Q ON I.quality=Q.name;

INSERT INTO Classes(name, powerType, media) SELECT C.name, P.id, M.id
FROM wowapi.Classes C INNER JOIN PowerTypes P ON C.powerType=P.name
INNER JOIN Medias M ON C.media=M.link;

INSERT INTO Races(name, faction) SELECT R.name, F.id FROM wowapi.Races R
INNER JOIN Factions F ON R.faction=F.name;

INSERT INTO Professions(name, description, type, media)
SELECT P.name, P.description, T.id, M.id FROM wowapi.Professions P
INNER JOIN Medias M ON P.media=M.link INNER JOIN Types T ON P.type=T.name;

INSERT INTO Players(name, isConnected, level, media, class, race, profession)
SELECT P.username, P.isConnected, P.level, M.id, P.class, P.race, P.profession
FROM wowapi.Players P INNER JOIN Medias M ON P.icon=M.link;

INSERT INTO Mounts(name, description, source, faction, media, player)
SELECT M.name, M.description, S.id, F.id, Me.id, M.player FROM wowapi.Mounts M
INNER JOIN Medias Me ON M.media=Me.link INNER JOIN Sources S ON M.source=S.name
INNER JOIN Factions F ON M.faction=F.name;

INSERT INTO PlayersItems(item, player, numberInInventory) SELECT item, player,
numberInInventory FROM wowapi.PlayersItems;