CREATE TABLE employees (
	name varchar,
	join_date date,
	age int,
	company varchar,
	email varchar,
	phone varchar,
	salary decimal(20,2),
	street varchar,
	city varchar,
	zipcode varchar,
	PRIMARY KEY(name, age)
);