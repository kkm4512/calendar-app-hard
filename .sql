create table user
(
    id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    name varchar(255) not null,
    email varchar(255) not null,
    primary key (id)
)

create table todo
(
    id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    title varchar(255) not null,
    author varchar(255) not null,
    detail varchar(255) not null,
    primary key (id),
    foreign key (user_id) references user(id)
)

create table comment
(
    id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    author varchar(255) not null,
    detail varchar(255) not null,
    primary key (id),
    foreign key (todo_id) references todo(id)
)

