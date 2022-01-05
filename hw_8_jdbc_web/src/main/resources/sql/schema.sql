drop table if exists customer_product;
drop table if exists customers;
drop table if exists products;

create table customers
(
    id         bigint auto_increment
        primary key,
    created    datetime(6) null,
    updated    datetime(6) null,
    visible    bit null,
    first_name varchar(255) not null,
    last_name  varchar(255) not null
);

create table products
(
    id               bigint auto_increment
        primary key,
    created          datetime(6)  null,
    updated          datetime(6)  null,
    visible          bit          null,
    product_name varchar(255) not null,
    price int not null
);

create table customer_product
(
    customer_id bigint not null,
    product_id bigint not null,
    primary key (customer_id, product_id),
    foreign key (product_id) references products(id),
    foreign key (customer_id) references customers(id)
);