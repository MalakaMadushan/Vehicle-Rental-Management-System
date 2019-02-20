show databases ;
create database CRMS;
use crms;
create table if not exists Customer(
  nic varchar(10) primary key ,
  name varchar(50) unique ,
  address VARCHAR(100) NOT NULL ,
  telephone VARCHAR(11) NOT NULL

);

create table if not exists Vehicle(
  vehicle_number VARCHAR(10) primary key ,
  v_name VARCHAR(50) NOT NULL ,
  model VARCHAR(50) not null ,
  type VARCHAR(50) not null ,
  vehicle_rate DECIMAL(10,2) NOT NULL,
  colour varchar(20) not null

);

create table if not exists Rent(
  c_nic VARCHAR(10),
  v_num VARCHAR(10),
  rent_date DATE NOT NULL ,
  return_date DATE NOT NULL ,
  duration INT,
  CONSTRAINT pk_Rent PRIMARY KEY (c_nic,v_num),
  CONSTRAINT fk_Customer FOREIGN KEY (c_nic) REFERENCES Customer(nic),
  CONSTRAINT  fk_Vehicle FOREIGN KEY (v_num) REFERENCES Vehicle(vehicle_number)

);

create table if not exists Payment(
  pid varchar(10) primary key ,
  rent_date DATE ,
  return_date DATE ,
  date DATE,
  rental_amount DECIMAL(10,2),
  advanced DECIMAL(10,2),
  extra DECIMAL(10,2),
  net_total DECIMAL(10,2),
  c_nic VARCHAR(10),
  v_num VARCHAR(10),
  CONSTRAINT fk_payement FOREIGN KEY(c_nic,v_num) REFERENCES Rent(c_nic,v_num)
);

Join Query

SELECT vehicle_number,v_name,model,type,vehicle_rate,colour FROM Vehicle v INNER JOIN Rent R on v.vehicle_number != R.v_num WHERE R.v_num in
(2018-12-09 between rent_date AND return_date AND 2018-12-12 between return_date AND return_date);


SELECT rent_date,return_date,name,address,telephone,model,type,colour,vehicle_rate FROM Customer INNER JOIN Rent ON Customer.nic=Rent.c_nic INNER JOIN Vehicle ON
    Rent.v_num=Vehicle.vehicle_number WHERE Rent.c_nic='932040662v' AND Rent.v_num='AD-8523';