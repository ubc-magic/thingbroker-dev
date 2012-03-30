/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.things.impl;

import org.springframework.jdbc.core.JdbcTemplate;

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
	
	private final static String SELECT_ID_BY_NAME = "";

}
