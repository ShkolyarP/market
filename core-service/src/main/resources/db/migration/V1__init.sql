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
    price       numeric(8, 2),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
insert into products (title, price, category_id)
values ('Bread', 7.80, 1),
       ('Milk', 18.90, 1),
       ('Eggs', 23.30, 1),
       ('Water', 5.50, 1),
       ('Apples', 13.00, 1),
       ('Bananas', 7.30, 1),
       ('Juice', 11.90, 1),
       ('Pizza', 13.30, 1),
       ('Mango', 15.0, 1),
       ('Oranges', 2.00, 1),
       ('Wine', 4.80, 1),
       ('Potatoes', 2.90, 1),
       ('Chocolate', 11.30, 1),
       ('Peach', 8.50, 1),
       ('Salad', 4.00, 1),
       ('Ice cream', 15.80, 1),
       ('Chicken', 19.90, 1),
       ('Meat', 60.30, 1),
       ('Fish', 45.50, 1),
       ('Beer', 10.00, 1);


create table orders
(
    id          bigserial primary key,
    username    varchar(255)  not null,
    total_price numeric(8, 2) not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint  not null references products (id),
    order_id          bigint  not null references orders (id),
    quantity          int     not null,
    price_per_product numeric(8, 2) not null,
    price             numeric(8, 2) not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);