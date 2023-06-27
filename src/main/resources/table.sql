create table stadium_tb(
    stadium_id int AUTO_INCREMENT primary key,
    name varchar(20) not null,
    stadium_created_at timestamp not null
);

create table team_tb(
    id int auto_increment primary key,
    stadium_id int foreign key,
    team_name varchar(20) not null,
    team_created_at timestamp not null
);