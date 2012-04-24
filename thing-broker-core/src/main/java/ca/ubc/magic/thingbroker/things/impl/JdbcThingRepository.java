/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.things.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import ca.ubc.magic.thingbroker.things.Thing;

/**
 *
 * @author Mike Blackstock
 *
 */
public class JdbcThingRepository implements ThingRepository {

	final private JdbcTemplate jdbcTemplate;
	
	public JdbcThingRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* (non-Javadoc)
	 * @see ca.ubc.magic.thingbroker.things.impl.ThingRepository#getId(java.lang.String)
	 */
	public Long getId(String name) {
		return jdbcTemplate.queryForLong(SELECT_ID_BY_NAME);
	}
	
	public List<Thing> getThings(String name) {
		String sqlName = name.replace('*', '%');
		return jdbcTemplate.query(SELECT_THINGS_BY_NAME, new ThingRowMapper(), sqlName);
	}
	
	private static class ThingRowMapper implements RowMapper<Thing> {

		public Thing mapRow(ResultSet rs, int rowNum) throws SQLException {
			Thing newThing = new Thing();
			
			newThing.setId(rs.getLong("id"));
			newThing.setName(rs.getString("name"));
			return newThing;
		}
	}
	
	private final static String SELECT_ID_BY_NAME = "select id from things where name=?";
	private final static String SELECT_THINGS_BY_NAME = "select * from things where name like ?";
}
