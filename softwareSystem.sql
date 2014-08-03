CREATE DATABASE IF NOT EXISTS software_system_db;

USE software_system_db;

DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS products;


CREATE TABLE customers (
		customerID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		firstName varchar (15) NOT NULL,
		lastName varchar (15) NOT NULL,
		address varchar (50) NOT NULL,
		phoneNum varchar (15) NOT NULL);
		
CREATE TABLE products (
		productID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		productName varchar (30) NOT NULL,
		description varchar (30) NOT NULL,
		price double (10,2) NOT NULL);

insert into customers (firstName,lastName,address,phoneNum) values ('John','Adams','2149 Stadium Dr.','(828)449-4179');
insert into customers (firstName,lastName,address,phoneNum) values ('Peyton','Manning','556 Hwy 158 N.','(212)220-4149');
insert into customers (firstName,lastName,address,phoneNum) values ('Tony','Romo','445 Giga Lane','(336)778-5134');
insert into customers (firstName,lastName,address,phoneNum) values ('Ray','Rice','9001 South Main St.','(229)766-2043');

insert into products (productName,description,price) values ('Use Your Illusion','Guns N Roses','19.00');
insert into products (productName,description,price) values ('Ride The Lightning','Metallica','12.00');
insert into products (productName,description,price) values ('Foreverly','Billy Joe and Narah','10.00');
insert into products (productName,description,price) values ('Two Lanes Of Freedom','Tim McGraw','9.00');

