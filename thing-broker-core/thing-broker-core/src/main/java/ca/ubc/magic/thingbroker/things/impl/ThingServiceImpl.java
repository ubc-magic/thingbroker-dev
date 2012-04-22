/**
 * Copyright (c) 2012 Media and Graphics Interdisciplinary Centre
 * University of British Columbia.
 */
package ca.ubc.magic.thingbroker.things.impl;

import java.util.List;
import java.util.Map;

import ca.ubc.magic.thingbroker.things.Thing;
import ca.ubc.magic.thingbroker.things.ThingService;


/**
 *
 * @author Mike Blackstock
 *
 */
public class ThingServiceImpl implements ThingService {

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#getThings(java.lang.String)
	 */
	public List<Thing> getThings(String name) {
		
		// translate name into MySQL wildcard
		
		// TODO: not implemented yet.
		throw new UnsupportedOperationException("TODO: Not implemented yet.");
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#updateThing(java.lang.String, java.util.Map)
	 */
	public Thing updateThing(String name, Map<String, Object> metaData) {
		// TODO: not implemented yet.
		throw new UnsupportedOperationException("TODO: Not implemented yet.");
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#insertThing(java.lang.String, java.util.Map)
	 */
	public Thing insertThing(String name, Map<String, Object> metaData) {
		// TODO: not implemented yet.
		throw new UnsupportedOperationException("TODO: Not implemented yet.");
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#deleteThing(java.lang.String)
	 */
	public void deleteThing(String name) {
		// TODO: not implemented yet.
		throw new UnsupportedOperationException("TODO: Not implemented yet.");
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#findThings(java.lang.String)
	 */
	public List<Thing> queryThings(String query) {
		// TODO: generate query suitable for repository or just delegate
		
		// TODO: not implemented yet.
		throw new UnsupportedOperationException("TODO: Not implemented yet.");
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#addMetaDataIndex(java.lang.String, java.lang.String)
	 */
	public void addMetaDataIndex(String name, String fieldName) {
		// TODO: not implemented yet.
		throw new UnsupportedOperationException("TODO: Not implemented yet.");
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#addStateIndex(java.lang.String, java.lang.String)
	 */
	public void addStateIndex(String name, String fieldName) {
		// TODO: not implemented yet
		throw new UnsupportedOperationException("TODO: Not implemented yet.");
	}

}
