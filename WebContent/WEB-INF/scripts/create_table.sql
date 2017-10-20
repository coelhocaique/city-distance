create database city_distance;
use city_distance;

CREATE TABLE IF NOT EXISTS city (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  latitude DOUBLE(30,10) NOT NULL,
  longitude DOUBLE(30,10) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

insert into city values (1,'Sao Paulo',-23.550520,-46.633309);
insert into city values (2,'London',51.507351,-0.127758);
insert into city values (3,'New York',40.712775,-74.005973);
insert into city values (4,'Buenos Aires',-34.603684,-58.381559);
insert into city values (5,'Toronto',43.653226,-79.383184);
insert into city values (6,'Madrid',40.416775,-3.703790);

commit;

-- src lats: https://www.latlong.net/