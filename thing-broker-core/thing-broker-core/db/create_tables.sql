SET foreign_key_checks = 0;

DROP TABLE IF EXISTS things;
DROP TABLE IF EXISTS state;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS event_subscription;

-- things

create table things (
	id bigint auto_increment not null unique,
  	name varchar(255) not null unique,
  	meta_data varchar(255),
  	state varchar(255),
  	PRIMARY KEY (id),
  	UNIQUE KEY (name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- thing state and field schema

create table state (
	thing_id bigint not null,
	time_stamp bigint not null,
	name varchar(255) not null,
	state_value varchar(255),
	foreign key (thing_id) references things(id) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- thing events

create table events (
	id bigint auto_increment not null unique,
	thing_id bigint not null,
	client_time_stamp bigint not null,
	server_time_stamp bigint not null,
	data varchar(255) not null,
	foreign key (thing_id) references things(id) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- event subscriptions

create table event_subscription (
	id bigint auto_increment not null unique,
	thing_id bigint not null,
	foreign key (thing_id) references things(id) on delete cascade
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

SET foreign_key_checks = 1;
