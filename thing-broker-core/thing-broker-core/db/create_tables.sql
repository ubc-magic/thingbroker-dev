-- things

create table things (
	id bigint auto_increment not null unique,
  	name varchar(255) NOT NULL unique,
  	meta_data varchar(255),
  	state varchar(255),
  	PRIMARY KEY (id),
  	UNIQUE KEY (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- thing data

create table data {
	id bigint auto_increment not null unique,
	thing_id bigint not null,
	client_time_stamp bigint not null,
	server_time_stamp bigint not null,
	data varchar(255) not null,
	foreign key (thing_id) references things(id) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- subscription to things for data

create table subscription {
	id bigint auto_increment not null unique,
	thing_id bigint not null,
	foreign key (thing_id) references things(id) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;