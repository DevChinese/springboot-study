-- noinspection SqlNoDataSourceInspectionForFile

use springboot;

CREATE TABLE if not exists `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `userName` varchar(32) NOT NULL,
  `passWord` varchar(50) NOT NULL,
  `realName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

insert into
`user` (`userName`, `passWord`, `realName`)
values ('huazai', 'huazai', 'huazai');

SELECT * FROM `user`;