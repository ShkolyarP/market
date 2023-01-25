create table products
(
    id         bigserial primary key,
    title      VARCHAR(255),
    price      DOUBLE,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
insert into products (title, price)
values ('Bread', 7.8),
       ('Milk', 18.9),
       ('Eggs', 23.3),
       ('Water', 5.5),
       ('Apples', 13.0),
       ('Bananas', 7.3),
       ('Juice', 11.9),
       ('Pizza', 13.3),
       ('Mango', 15.5),
       ('Oranges', 2.0),
       ('Wine', 4.8),
       ('Potatoes', 2.9),
       ('Chocolate', 11.3),
       ('Peach', 8.5),
       ('Salad', 4.0),
       ('Ice cream', 15.8),
       ('Chicken', 19.9),
       ('Meat', 60.3),
       ('Fish', 45.5),
       ('Beer', 10.0);

create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table user_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_MANAGER');

insert into users(username, password, email)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob@gmail.com'),
       ('dick', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'dick@gmail.com');

insert into user_roles (user_id, role_id)
values (1, 1),
       (2, 2);

