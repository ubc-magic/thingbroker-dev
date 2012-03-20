/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.things.impl;

/**
 * Repository for things.
 * 
 * @author Mike Blackstock
 *
 */
public interface ThingRepository {

	/**
	 * Get the thing id from the name.
	 * 
	 * @param name
	 * @return the thing id, or null if it does not exist.
	 */
	Long getId(String name);

}
