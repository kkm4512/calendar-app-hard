create table member (
    id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    author varchar(255),
    email varchar(255),
    password varchar(255),
    primary key (id)
)

create table todo (
    id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    detail varchar(255),
    title varchar(255),
    member_id bigint,
    primary key (id)
)

create table comment (
    id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    detail varchar(255),
    todo_id bigint,
    primary key (id)
)

create table calendar (
    id bigint not null auto_increment,
    created_at datetime(6),
    updated_at datetime(6),
    worker_id bigint,
    member_id bigint,
    todo_id bigint,
    primary key (id)
)

