use s2emp;
drop table if exists music;
create table music (
  songId int unsigned not null auto_increment,
  Title varchar(48) not null,
  Artist varchar(24) not null,
  Album varchar(24) not null,
  Year double not null,
  primary key(songId)
);
