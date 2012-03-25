/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.things.impl;

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

}
