alter table stadium_tb modify stadium_id int auto_increment;

alter table team_tb add FOREIGN KEY(stadium_id) references stadium_tb(stadium_id) on UPDATE cascade;

select * from stadium_tb;

select * from team_tb;

SELECT team_tb.id, stadium_tb.name, team_tb.team_name FROM team_tb JOIN stadium_tb ON team_tb.stadium_id = stadium_tb.stadium_id;
set FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE stadium_tb;

TRUNCATE TABLE team_tb;

delete from team_tb where id>1;

select * from player_tb;

SELECT * FROM player_tb where team_id = 1;

SELECT team_name FROM team_tb where id=1;
select * from out_player_tb;
UPDATE player_tb SET team_id=1 where id = 1;