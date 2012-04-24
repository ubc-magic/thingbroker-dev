/**
 * Copyright (c) 2012 Media and Graphics Interdisciplinary Centre
 * University of British Columbia.
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
	List<Map<String, StateField>> getAllState(String thing);
	
	/**
	 * 
	 * PUT /state/{thing}
	 * 
	 * @param thing no wildcards
	 * @param state all state
	 */
	void updateAllState(String thing, Map<String, StateField> state);
	
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
	StateField getState(String thing, String field);
	
	/**
	 * 
	 * PUT /state/{thing}/{field}
	 * 
	 * @param thing
	 * @param field
	 * @param state
	 */
	void updateState(String thing, String field, StateField state);
	
	/**
	 * 
	 * DELETE /state/{thing}/{field}
	 * 
	 * @param thing
	 * @param field
	 */
	void deleteState(String thing, String field);
		
}
