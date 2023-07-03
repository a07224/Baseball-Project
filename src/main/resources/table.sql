create table stadium_tb(
    stadium_id int AUTO_INCREMENT primary key,
    name varchar(20) not null,
    stadium_created_at timestamp not null
);

create table team_tb(
    id int auto_increment primary key,
    stadium_id int,
    team_name varchar(20) not null,
    team_created_at timestamp not null,
    foreign key (stadium_id)
    REFERENCES stadium_tb(stadium_id) ON UPDATE CASCADE
);

create table player_tb(
    id int auto_increment primary key,
    team_id int,
    name varchar(20) not null,
    position varchar(20) not null,
    player_created_at timestamp not null,
    foreign key (team_id)
    REFERENCES team_tb(id)ON UPDATE CASCADE,
    UNIQUE (team_id, position)
);

create table out_player_tb(
    id int auto_increment primary key,
    player_id int,
    reason varchar(20) not null,
    out_player_created_at timestamp not null,
    foreign key (player_id)
    REFERENCES player_tb(id)ON UPDATE CASCADE
);