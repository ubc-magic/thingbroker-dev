/**
 * Copyright (c) 2012 Media and Graphics Interdisciplinary Centre
 * University of British Columbia.
 */
package ca.ubc.magic.thingbroker.things.impl;

import java.util.List;

import ca.ubc.magic.thingbroker.things.Thing;

/**
 *
 * @author Mike Blackstock
 *
 */
public class MockThingRepository implements ThingRepository {

	/* (non-Javadoc)
	 * @see ca.ubc.magic.thingbroker.things.impl.ThingRepository#getId(java.lang.String)
	 */
	public Long getId(String name) {
		return 1L;
	}

	public List<Thing> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Thing> getThings(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
