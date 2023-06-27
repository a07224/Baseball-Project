alter table stadium_tb modify stadium_id int auto_increment;

alter table team_tb add FOREIGN KEY(stadium_id) references stadium_tb(stadium_id) on UPDATE cascade;

select * from stadium_tb;