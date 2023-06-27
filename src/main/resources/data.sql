alter table stadium_tb modify stadium_id int auto_increment;

alter table team_tb add FOREIGN KEY(stadium_id) references stadium_tb(stadium_id) on UPDATE cascade;

select * from stadium_tb;

select * from team_tb;

SELECT team_tb.id, stadium_tb.name, team_tb.team_name FROM team_tb JOIN stadium_tb ON team_tb.stadium_id = stadium_tb.stadium_id;

