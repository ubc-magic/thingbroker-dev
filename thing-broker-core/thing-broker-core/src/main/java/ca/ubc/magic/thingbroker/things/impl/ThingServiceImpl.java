/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
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
		
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#updateThing(java.lang.String, java.util.Map)
	 */
	public Thing updateThing(String name, Map<String, Object> metaData) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#insertThing(java.lang.String, java.util.Map)
	 */
	public Thing insertThing(String name, Map<String, Object> metaData) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#deleteThing(java.lang.String)
	 */
	public void deleteThing(String name) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#findThings(java.lang.String)
	 */
	public List<Thing> queryThings(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#addMetaDataIndex(java.lang.String, java.lang.String)
	 */
	public void addMetaDataIndex(String name, String fieldName) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.thingbroker.ThingService#addStateIndex(java.lang.String, java.lang.String)
	 */
	public void addStateIndex(String name, String fieldName) {
		// TODO Auto-generated method stub

	}

}
