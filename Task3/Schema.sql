CREATE TABLE product(
	id SERIAL PRIMARY KEY,
	name VARCHAR(64) NOT NULL,
	price INTEGER CHECK(price >= 0) NOT NULL
);

insert into product (name, price) values ('Phone' , 699);

insert into product (name,price) values ('Помидор' , 2.99);

select * from product;