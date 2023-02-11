create table categories
(
    id         bigserial primary key,
    title      VARCHAR(255) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories(title)
values ('Food'),
       ('Other');


create table products
(
    id          bigserial primary key,
    title       VARCHAR(255),
    category_id bigint references categories (id),
    price       decimal,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
insert into products (title, price, category_id)
values ('Bread', 7.8, 1),
       ('Milk', 18.9, 1),
       ('Eggs', 23.3, 1),
       ('Water', 5.5, 1),
       ('Apples', 13.0, 1),
       ('Bananas', 7.3, 1),
       ('Juice', 11.9, 1),
       ('Pizza', 13.3, 1),
       ('Mango', 15.5, 1),
       ('Oranges', 2.0, 1),
       ('Wine', 4.8, 1),
       ('Potatoes', 2.9, 1),
       ('Chocolate', 11.3, 1),
       ('Peach', 8.5, 1),
       ('Salad', 4.0, 1),
       ('Ice cream', 15.8, 1),
       ('Chicken', 19.9, 1),
       ('Meat', 60.3, 1),
       ('Fish', 45.5, 1),
       ('Beer', 10.0, 1);


create table orders
(
    id          bigserial primary key,
    username    varchar(255) not null,
    total_price DOUBLE not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);