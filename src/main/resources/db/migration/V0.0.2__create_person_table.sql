CREATE TABLE person(
	id uuid PRIMARY KEY,
	first_name varchar (255),
	last_name varchar(255),
	address_id uuid,
	FOREIGN KEY (address_id) REFERENCES address (ID)
);