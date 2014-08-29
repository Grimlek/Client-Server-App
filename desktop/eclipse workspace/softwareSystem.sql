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
insert into customers (firstName,lastName,address,phoneNum) values ('Peyton','Setzer','556 Hwy 158 N.','(212)220-4149');
insert into customers (firstName,lastName,address,phoneNum) values ('Tony','Ramerez','445 Giga Lane','(336)778-5134');
insert into customers (firstName,lastName,address,phoneNum) values ('Ray','Sexton','9001 South Main St.','(229)766-2043');
insert into customers (firstName,lastName,address,phoneNum) values ('Charles','Smith','1004 Steeplewood Dr','(212)220-4149');
insert into customers (firstName,lastName,address,phoneNum) values ('Wanda','Pruitt','637 Sandy Trail','(817)237-4196');
insert into customers (firstName,lastName,address,phoneNum) values ('Linda','Cole','712 Admiralty Way','(817)297-7597');
insert into customers (firstName,lastName,address,phoneNum) values ('Amy','Watts','1120 Oakbend Lane ','(918)282-4016');
insert into customers (firstName,lastName,address,phoneNum) values ('Mike','Tomlin','1409 Lamplighter Lane','(903)756-4082');
insert into customers (firstName,lastName,address,phoneNum) values ('Jeff','Hunt','7820 Sheridan Rd','(203)375-9757');
insert into customers (firstName,lastName,address,phoneNum) values ('Will','Brandon','6430 Stream Side Court','(972)441-4715');
insert into customers (firstName,lastName,address,phoneNum) values ('Mike','Foster','717 Vickie Drive','(409)267-3312');
insert into customers (firstName,lastName,address,phoneNum) values ('Michelle','Owens','6136 Walraven Cir','(409)866-6262');
insert into customers (firstName,lastName,address,phoneNum) values ('Josh','Hunter','2342 Robinhood Dr','(936)336-3508');
insert into customers (firstName,lastName,address,phoneNum) values ('Paul','Carlson','6136 San Villa Drive Apt. C','(214)348-8906');
insert into customers (firstName,lastName,address,phoneNum) values ('Jasmine','Tate','5216 S. South Dr','(446)237-9401');
insert into customers (firstName,lastName,address,phoneNum) values ('Jennifer','Meyers','5029 Jerri Lane','(819)274-0840');

insert into products (productName,description,price) values ('M66-6810','Metal Tubing','19.90');
insert into products (productName,description,price) values ('F66-2300-100','Metal Tubing','17.26');
insert into products (productName,description,price) values ('F66-2346','Metal Tubing','10.02');
insert into products (productName,description,price) values ('M66-6856','Metal Tubing','6.23');
insert into products (productName,description,price) values ('05-2781-001','Metal Tubing','3.56');
insert into products (productName,description,price) values ('M46-6182','Metal Tubing','16.77');
insert into products (productName,description,price) values ('M46-6181','Metal Tubing','21.30');
insert into products (productName,description,price) values ('M65-4451-001','Metal Tubing','29.35');
insert into products (productName,description,price) values ('M65-4451-000','Metal Tubing','25.67');
insert into products (productName,description,price) values ('25-3987-CHRM','Metal Tubing','89.23');
insert into products (productName,description,price) values ('210-9981','Metal Flange','21.72');
insert into products (productName,description,price) values ('210-5547','Metal Flange','12.67');
insert into products (productName,description,price) values ('210-2122','Metal Flange','19.88');
insert into products (productName,description,price) values ('210-4566','Metal Flange','23.14');
insert into products (productName,description,price) values ('205-9981','Metal Bracket','9.12');
insert into products (productName,description,price) values ('205-2300','Metal Bracket','5.12');
insert into products (productName,description,price) values ('205-56-CHRMM','Metal Bracket','14.89');
insert into products (productName,description,price) values ('205-9088','Metal Bracket','6.43');
