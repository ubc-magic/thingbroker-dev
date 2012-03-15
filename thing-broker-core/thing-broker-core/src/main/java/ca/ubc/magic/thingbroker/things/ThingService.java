/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.things;

import java.util.List;
import java.util.Map;

/**
 * Thing service interface for creating, updating, finding and deleting things.
 * 
 * @author Mike Blackstock
 * 
 */
public interface ThingService {

	/**
	 * Get the things by name
	 * 
	 * If the name is a number, assumes its the thing id, if it is a string, it
	 * gets the named thing. If it contains a wildcard (e.g. a.*.b), it gets all things that
	 * match.
	 * 
	 * GET /things/{name}
	 * 
	 * @param name
	 * @return the thing
	 */
	List<Thing> getThings(String name);

	/**
	 * Update the thing by name (no wildcard)
	 * 
	 * PUT /things/{name}
	 * 
	 * @param id
	 * @return updatedThing
	 */
	Thing updateThing(String name, Map<String, Object> metaData);

	/**
	 * Create new thing by name (no wildcard).
	 * 
	 * POST /things/{name}
	 * 
	 * @param id
	 * @return created thing
	 */
	Thing insertThing(String name, Map<String, Object> metaData);

	/**
	 * Delete the thing by name.  No wildcard.
	 * 
	 * DELETE /things/{name}
	 * 
	 * Implementation will determine appropriate call by id or name.
	 * 
	 * @param id
	 * @return
	 */
	void deleteThing(String name);

	/**
	 * Find things that match the where clause of the query.
	 * 
	 * Implementation will delegate
	 * 
	 * GET /things?q={query}
	 * 
	 * @param query for things
	 * @return
	 */
	List<Thing> queryThings(String query);

	/**
	 * Add an index to a meta data field so it can be queried.
	 * 
	 * @param name thing name (no wildcard)
	 * @param fieldName
	 */
	void addMetaDataIndex(String name, String fieldName);

	/**
	 * Add an index to the state so that we can query things based on the state
	 * fields.
	 * 
	 * @param name (no wildcard)
	 * @param fieldName
	 */
	void addStateIndex(String name, String fieldName);

}
