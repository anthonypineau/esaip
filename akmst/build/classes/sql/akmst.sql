DROP TABLE IF EXISTS `Users`;
DROP TABLE IF EXISTS `Customers`;
DROP TABLE IF EXISTS `Quotations`;
DROP TABLE IF EXISTS `Invoices`;

create table Users
(id int not null auto_increment,
name varchar(45) not null,
status varchar(45) not null,
email varchar(45) unique not null,
password varchar(45) not null,
constraint pk_Users primary key(id))
ENGINE=INNODB;

create table Customers
(number int not null auto_increment,
name varchar(45) not null,
email varchar(45) not null,
phone varchar(45) not null,
orderDate varchar(10) not null,
tag varchar(10) not null,
constraint pk_Customers primary key(number))
ENGINE=INNODB;

create table Quotations
(number int not null auto_increment,
date varchar(10) not null,
price int,
customer int not null,
constraint pk_Quotations primary key(number),
FOREIGN KEY (customer) REFERENCES Customers(number))
ENGINE=INNODB;

create table Invoices
(number int not null auto_increment,
date varchar(10) not null,
totalPrice int,
customer int not null,
constraint pk_Invoices primary key(number),
FOREIGN KEY (customer) REFERENCES Customers(number))
ENGINE=INNODB;