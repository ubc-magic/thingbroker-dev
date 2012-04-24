-- things
-- name part1, part2, part3 for quick queries

create table things (
	id bigint auto_increment not null unique,
  	name varchar(255) not null unique,
  	meta_data varchar(255),
  	PRIMARY KEY (id),
  	UNIQUE KEY (name)
);

-- thing state fields

create table state_fields (
	thing_id bigint not null,
	time_stamp bigint not null,
	name varchar(255) not null,
	str_value varchar(255) comment 'string value of state field',
	num_value double comment 'numeric value of state field',
	foreign key (thing_id) references things(id) on delete cascade
);

-- thing events
-- other reserved data fields

create table events (
	id bigint auto_increment not null unique,
	thing_id bigint not null,
	client_time_stamp bigint not null,
	server_time_stamp bigint not null,
	data varchar(255) not null,
	foreign key (thing_id) references things(id) on delete cascade
);

-- index table, contains number and/or string value of a field buried in data

create table event_field_index (
	event_id bigint not null,
	field_name varchar(255) not null,
	field_value double,
	field_value_string varchar(255),
	foreign key (event_id) references events(id) on delete cascade
);

-- event subscriptions

create table event_subscription (
	id bigint auto_increment not null unique,
	thing_id bigint not null,
	foreign key (thing_id) references things(id) on delete cascade
);