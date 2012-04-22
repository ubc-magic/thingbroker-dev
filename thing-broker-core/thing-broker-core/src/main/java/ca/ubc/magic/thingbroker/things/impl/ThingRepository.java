/**
 * Copyright (c) 2012 Media and Graphics Interdisciplinary Centre
 * University of British Columbia.
 */
package ca.ubc.magic.thingbroker.things.impl;

import java.util.List;

import ca.ubc.magic.thingbroker.things.Thing;

/**
 * Repository for storing things.
 * 
 * @author Mike Blackstock
 *
 */
public interface ThingRepository {

	/**
	 * Get the thing id from the name.  Needed by the EventService to send events.
	 * 
	 * @param name
	 * @return the thing id, or null if it does not exist.
	 */
	Long getId(String name);
	
	/**
	 * Get the thing(s) that match the name pattern which may include wildcards.
	 * 
	 * @param name
	 * @return
	 */
	List<Thing> getThings(String name);
	
}
