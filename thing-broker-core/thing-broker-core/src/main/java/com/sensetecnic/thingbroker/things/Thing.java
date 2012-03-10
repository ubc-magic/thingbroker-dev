package com.sensetecnic.thingbroker.things;

import java.util.Map;

public class Thing {
	
	private String name;
	
	private Map<String, Object> metaData;
	
	private Map<String, StateField> state;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Metadata: information about the thing.
	 * 
	 * @return
	 */
	public Map<String, Object> getMetaData() {
		return metaData;
	}

	public void setMetaData(Map<String, Object> metaData) {
		this.metaData = metaData;
	}

	/**
	 * State: last time stamped data values (state) of the thing.
	 * 
	 * @return
	 */
	public Map<String, StateField> getState() {
		return state;
	}

	/**
	 * Update the last timestamped data values (state) of the thing.
	 * 
	 * @param state
	 */
	public void setState(Map<String, StateField> state) {
		this.state = state;
	}
}
