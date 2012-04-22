/**
 * Copyright (c) 2012 Media and Graphics Interdisciplinary Centre
 * University of British Columbia.
 */
package ca.ubc.magic.thingbroker.things;

import java.util.Map;

import ca.ubc.magic.thingbroker.state.StateField;

/**
 * A Thing is a container for a person, place or object with a name, information about the thing (metadata),
 * and its state.
 * 
 * @author Mike Blackstock
 *
 */
public class Thing {
	
	private String name;
	
	private Long id;
	
	private Map<String, Object> metaData;
	
	private Map<String, StateField> state;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Metadata: information about the thing.
	 */
	public Map<String, Object> getMetaData() {
		return metaData;
	}

	public void setMetaData(Map<String, Object> metaData) {
		this.metaData = metaData;
	}

	/**
	 * State: last time stamped event values (state) of the thing.
	 * 
	 * @return
	 */
	public Map<String, StateField> getState() {
		return state;
	}
}
