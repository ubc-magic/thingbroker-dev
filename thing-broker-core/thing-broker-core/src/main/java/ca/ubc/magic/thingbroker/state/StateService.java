/**
 * Copyright (c) 2012 Sense Tecnic Systems, Inc. All rights reserved.
 */
package ca.ubc.magic.thingbroker.state;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Mike Blackstock
 *
 */
public interface StateService {

	// ~--- all state fields
	
	/**
	 * 
	 * GET /state/{thing}
	 * 
	 * @param thing including wildcards
	 * 
	 * @return
	 */
	List<Map<String, State>> getAllState(String thing);
	
	/**
	 * 
	 * PUT /state/{thing}
	 * 
	 * @param thing no wildcards
	 * @param state all state
	 */
	void updateAllState(String thing, Map<String, State> state);
	
	/**
	 * 
	 * DELETE /state/{thing} no wildcards
	 * 
	 * @param thing
	 */
	void deleteAllState(String thing);
	
	// ~--- individual state fields
	
	/**
	 * 
	 * GET /state/{thing}/{field}
	 * 
	 * @param thing
	 * @param field
	 * @return
	 */
	State getState(String thing, String field);
	
	/**
	 * 
	 * PUT /state/{thing}/{field}
	 * 
	 * @param thing
	 * @param field
	 * @param state
	 */
	void updateState(String thing, String field, State state);
	
	/**
	 * 
	 * DELETE /state/{thing}/{field}
	 * 
	 * @param thing
	 * @param field
	 */
	void deleteState(String thing, String field);
		
}
